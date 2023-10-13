package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admin;
import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
	public Admin findByUsername(String username);
}
