package com.netease.network.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "shoppingcart")
@Entity
public class ShoppingCart implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(length = 11)
	private int productId;

	@Column(length = 50)
	private String productName;

	@Column(length = 11)
	private int count;

	@Column(length = 15)
	private Double price;

	@Column(length = 11)
	private int cnt;//判断是否已经有该商品
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
