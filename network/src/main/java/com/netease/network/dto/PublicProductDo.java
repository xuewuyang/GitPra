package com.netease.network.dto;

import java.io.Serializable;

public class PublicProductDo implements Serializable {

    private int id;
    private String productName;
    private String productSummary;
    private String pictureAddress;
    private String productContext;
    private String price;
    private String buyTime;
    private int cnt;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCnt() {
        return cnt;
    }
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
}
