package com.example.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@NamedNativeQuery(name="findByPurchaseDate",
//query = "select * from purchase p  where p.purchase_date>=cast(:date1 as DATE)"
//		+ " and p.purchase_date<=cast(:date1 as DATE)",resultClass = Purchase.class)
public class Purchase {
	@Id
	@GeneratedValue
	private int purchaseId;
	private String userId;
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	private int totalAmt;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns =
	@JoinColumn(referencedColumnName = "purchaseId"),
	inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	private Set<PurchaseItems> purchaseItems;

	

	public Set<PurchaseItems> getPurchaseItems() {
		return purchaseItems;
	}

	
	public void setPurchaseItems(Set<PurchaseItems> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(int totalAmt) {
		this.totalAmt = totalAmt;
	}

}
