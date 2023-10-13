package com.example.demo.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Purchase;
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer>{
public List<Purchase> findByUserId(String userId);
@Query(value="select * from purchase p where "
		+ "cast(p.purchase_date as DATE)>=cast(:date1 as DATE)"
		+ "and cast(p.purchase_date as DATE)<=cast(:date1 as DATE)",nativeQuery = true)
public List<Purchase> findByPurchaseDate(@Param("date1")Date date1);
}

