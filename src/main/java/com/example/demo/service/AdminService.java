package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.repo.AdminRepo;

@Service
public class AdminService {
	@Autowired
	private AdminRepo adminRepo;

	public AdminRepo getAdminRepo() {
		return adminRepo;
	}

	public void setAdminRepo(AdminRepo adminRepo) {
		this.adminRepo = adminRepo;
	}

	public boolean authenticateAdmin(String username, String password) {
		Admin admin = adminRepo.findByUsername(username);
		if (admin != null) {
			if (admin.getPassword().equals(password))
				return true;
		}
		return false;
	}

}
