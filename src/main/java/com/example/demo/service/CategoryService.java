package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repo.CagtegoryRepo;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CagtegoryRepo cateRepo;

	public CagtegoryRepo getCateRepo() {
		return cateRepo;
	}

	public void setCateRepo(CagtegoryRepo cateRepo) {
		this.cateRepo = cateRepo;
	}

	public List<Category> getAllCategory() {
		return cateRepo.findAll();
	}
	public Category getCategoryByName(String cateName) {
		return cateRepo.findByCategoryName(cateName).get(0);
	}
	
}
