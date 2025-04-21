package com.profitkey.stock.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profitkey.stock.util.NicknameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service @Slf4j
public class GoogleOAuth2Service {

    private final RestTemplate restTemplate = new RestTemplate();
    /** Google OAuth2 토큰을 요청하는 URL **/
    /** (인가 코드를 사용하여 엑세스 토큰 발급) **/
    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenUrl;

    /**
     * Google OAuth2 사용자 정보를 요청하는 URL
     * (액세스 토큰을 사용하여 사용자 프로필 정보를 가져오는 엔드포인트)
     */
    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleUserInfoUrl;


    /**
     * Google OAuth2 클라이언트 ID
     * (Google Cloud Console에서 발급받은 애플리케이션의 클라이언트 ID)
     */
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;


    /**
     * Google OAuth2 클라이언트 시크릿
     * (Google Cloud Console에서 발급받은 애플리케이션의 클라이언트)
     */
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    /**
     * Google OAuth2 리디렉트 URI
     * (사용자가 Google 로그인 후, 인증 코드가 반환되는 애플리케이션의 URL)
     */
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    /**
     *
     * @param code
     * @return
     */
    public String getAccessToken(String code) {
        log.info("📌 받은 인가 코드: {}", code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        setAuthParams(code, params);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            log.info("📌 구글 토큰 요청 데이터: {}", params);
            log.info("📌 요청 URL: {}", googleTokenUrl);

            ResponseEntity<String> response = restTemplate.postForEntity(googleTokenUrl, request, String.class);

            log.info("✅ 구글 응답 코드: {}", response.getStatusCode());
            log.info("✅ 구글 응답 바디: {}", response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            log.error("❌ 구글 토큰 요청 실패", e);
            throw new RuntimeException("Failed to get Google Access Token");
        }
    }

    /**
     *
     * @param accessToken
     * @return
     */
    public Map<String, Object> getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(googleUserInfoUrl, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String id = jsonNode.get("sub").asText();
            String email = jsonNode.has("email") ? jsonNode.get("email").asText() : null;
            String nickname = jsonNode.has("name") ? jsonNode.get("name").asText() : NicknameUtil.generateRandomNickname();

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", id);
            userInfo.put("email", email);
            userInfo.put("nickname", nickname);

            return userInfo;
        } catch (Exception e) {
            log.error("❌ 구글 사용자 정보 요청 실패", e);
            throw new RuntimeException("Failed to get Google User Info");
        }
    }

    /**
     *
     * @param code
     * @param params
     */
    private void setAuthParams(String code, MultiValueMap<String, String> params) {
        if (params == null) {
            throw new IllegalArgumentException();
        }

        params.add("grant_type", "authorization_code");
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("redirect_uri", googleRedirectUri);
        params.add("code", code);
    }
}
