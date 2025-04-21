package com.example.apitest.exchange.ch3;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final SimplePasswordEncoder passwordEncoder;

	public void addUser(final String email, final String pw) {
		String encryptPassword = passwordEncoder.encryptPassword(pw);
		User user = User.builder()
			.email(email)
			.password(pw)
			.build();
		userRepository.save(user);
	}
}
