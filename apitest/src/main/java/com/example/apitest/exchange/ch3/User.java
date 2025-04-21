package com.example.apitest.exchange.ch3;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class User {
	private String name;
	private String email;
	private String password;
}
