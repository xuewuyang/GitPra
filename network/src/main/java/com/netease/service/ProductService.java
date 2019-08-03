package com.netease.service;

import java.util.List;

import com.netease.entity.Product;
import com.netease.vo.PublicProductVo;
import com.netease.vo.SoldItemVo;

public interface ProductService {

	//获得所有商品信息
	List<Product> getProducts();
	
	//获得单个商品的详细信息
	Product getProductById(int id);
	
	Boolean deleteProductById(int id);
	
	List<Product> getSoldProducts();
	
	List<Product> getNotBuyProducts();
	
	Boolean updateProduct(List<SoldItemVo> list);
	
	Boolean publicProduct(PublicProductVo productVo);
}
