package com.netease.network.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "product")
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(length = 50)
	private String productName;

	@Column(length = 250)
	private String productSummary;

	@Column(length = 120)
	private String pictureAddress;

	@Column(length = 250)
	private String productContext;

	@Column(length = 15)
	private double price;

	@Column(length = 11,columnDefinition = "int default 0")
	private int state;

	@Column(length = 11,columnDefinition = "int default 1")
	private int soldCount;

	@Column(length = 60)
	private String buyTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSummary() {
		return productSummary;
	}
	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}
	public String getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}
	public String getProductContext() {
		return productContext;
	}
	public void setProductContext(String productContext) {
		this.productContext = productContext;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSoldCount() {
		return soldCount;
	}
	public void setSoldCount(int soldCount) {
		this.soldCount = soldCount;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	
	
}
