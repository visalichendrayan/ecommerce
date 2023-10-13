package com.example.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private int price;
	private Date dateAdded;
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(referencedColumnName = "productId")
	,inverseJoinColumns = @JoinColumn(referencedColumnName = "categoryId"))
	private Set<Category> categoryIds;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public Set<Category> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(Set<Category> categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	

	

}
