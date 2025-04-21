package com.profitkey.stock.util;

import com.profitkey.stock.dto.KisApiProperties;
import com.profitkey.stock.service.stock.StockTokenService;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class HeaderUtil {
	private static KisApiProperties kisApiProperties;
	private static StockTokenService stockTokenService;

	public HeaderUtil(KisApiProperties kisApiProperties, StockTokenService stockTokenService) {
		HeaderUtil.kisApiProperties = kisApiProperties;
		HeaderUtil.stockTokenService = stockTokenService;
	}

	public static Map<String, String> getCommonHeaders() throws IOException {
		Map<String, String> headers = new HashMap<>();

		headers.put("Content-Type", "application/json");
		headers.put("authorization", "Bearer " + stockTokenService.getToken());
		headers.put("appKey", kisApiProperties.getApiKey());
		headers.put("appSecret", kisApiProperties.getSecretKey());
		return headers;
	}

	public static Map<String, String> getHeadersFromDto(Object dto) {
		Map<String, String> headers = new HashMap<>();
		Field[] fields = dto.getClass().getDeclaredFields();

		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(dto);
				headers.put(field.getName(), value == null ? "" : value.toString());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return headers;
	}

}
