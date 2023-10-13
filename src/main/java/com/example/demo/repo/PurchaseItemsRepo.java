package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PurchaseItems;

public interface PurchaseItemsRepo extends JpaRepository<PurchaseItems, Integer> {

}
