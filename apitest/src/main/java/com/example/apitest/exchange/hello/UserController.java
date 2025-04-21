package com.example.apitest.exchange.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	private UserService userService;
	public void savedUser(UserService userService, User user) {
		userService.enrollUser(user);
	}

	public void savedUser2(User user) {
		userService.enrollUser2(user);
	}

	public ResponseEntity<User> savedUser3(User user) {
		String userId = user.getId();
		String password = user.getPassword();
		User savedUser = userService.savedUser(userId, password);
		return ResponseEntity.ok(savedUser);

	}

}
