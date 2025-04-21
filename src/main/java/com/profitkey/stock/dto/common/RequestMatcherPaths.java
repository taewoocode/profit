package com.profitkey.stock.dto.common;

public class RequestMatcherPaths {
    public static final String[] PERMIT_ALL_PATHS = {
            "/",
            "/login",  // 로그인
            "/images/**",  // 이미지 파일
            "/js/**",  // JS 파일
            "/swagger/**",// Swagger
            "/swagger-ui.html",  // Swagger UI
            "/swagger-ui/**",  // Swagger UI 관련
            "/v3/api-docs/**",  // Swagger Docs
            "/v3/api-docs.yaml",  // Swagger Docs
            "/swagger-resources/**",  // Swagger 자원
            "/webjars/**",  // 웹 자원
            "/api/**",
            "/api/faq-category/**",
            "/api/login/oauth2/code/kakao",
            "/api-docs/**",
            "/api/oauth2/**",
    };

    public static final String[] AUTHENTICATED_PATHS = {
            "/api/board/**",
            "/api/social/**",
    };
}