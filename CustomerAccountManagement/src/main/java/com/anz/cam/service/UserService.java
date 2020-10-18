package com.anz.cam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anz.cam.model.User;
import com.anz.cam.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
}
