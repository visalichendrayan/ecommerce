package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
