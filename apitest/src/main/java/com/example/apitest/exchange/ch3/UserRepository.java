package com.example.apitest.exchange.ch3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String name, String password);

	void deleteByName(String name);
}
