package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

public interface CagtegoryRepo extends JpaRepository<Category, Integer>{
	
	public List<Category> findByCategoryName(String categoryName);

}
