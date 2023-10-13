package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepo prodRepo;

	@Autowired
	private CategoryService cateService;

	public boolean addProduct(Product prod) {
		Product pro = prodRepo.save(prod);
		return (pro == null) ? false : true;
	}

	public List<Product> getAllProducts() {
		return prodRepo.findAll();
	}

	public void deleteProductById(int id) {
		prodRepo.deleteById(id);
	}

	public List<Product> getProdByCategoryName(String category) {
		Category cate = cateService.getCategoryByName(category);
		List<Product> products = getAllProducts().stream().filter(e -> e.getCategoryIds().contains(cate)).collect(Collectors.toList());
				
		return products;

	}
}
