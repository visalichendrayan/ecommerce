package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public boolean authenticateUser(User user) {
		User u = userRepo.findByUsername(user.getUsername());
		if (u != null)
			return u.getPassword().equals(user.getPassword());
		return false;
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public boolean addUser(User user) {
		User u = userRepo.save(user);
		return (u != null) ? true : false;

	}

}
