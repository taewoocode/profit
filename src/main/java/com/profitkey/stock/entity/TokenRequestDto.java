package com.profitkey.stock.entity;

import lombok.Data;

@Data
public class TokenRequestDto {
	private String accessToken;
	private String refreshToken;
}
