package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseItems;
import com.example.demo.repo.PurchaseRepo;
@Service
@Transactional
public class PurchaseService {
	@Autowired
	private PurchaseRepo purchaseRepo;
	@Autowired
	private ProductService prodService;

	public boolean savePurchase(Purchase purchase) {
		Purchase purRes = purchaseRepo.save(purchase);
		return (purRes != null) ? true : false;
	}
	
	public List<Purchase> getAllOrders(){
		return purchaseRepo.findAll();
	} 
	
	public List<Purchase> getOrdersByUserId(String uname){
		return purchaseRepo.findByUserId(uname);
	}
	
	public List<Purchase> getOrderByDate(String date) throws ParseException{
		
	    List<Purchase> purList=purchaseRepo.findByPurchaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
	    return purList;
	}
	
	public List<Purchase> getOrdersByCategorty(String cateName){
		
		List<Product> productList=prodService.getProdByCategoryName(cateName);
		List<Purchase> purchaseList=new ArrayList<Purchase>();
		for(Purchase purchase:getAllOrders()) {
			Set<PurchaseItems> purItems=purchase.getPurchaseItems();
			for(PurchaseItems purItem:purItems) {
				if(productList.stream().anyMatch(e->e.getProductId()==purItem.getProductId())) {
					if(!purchaseList.contains(purchase))
						purchaseList.add(purchase);
				}
			}
		}
		return purchaseList;
	}
}
