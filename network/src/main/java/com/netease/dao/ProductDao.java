package com.netease.dao;

import java.util.List;

import com.netease.entity.Product;
import com.netease.vo.PublicProductVo;
import com.netease.vo.SoldItemVo;

public interface ProductDao {

	List<Product> getProducts();
	
	Product getProductById(int id);
	
	Integer deleteProductById(int id);
	
	List<Product> getSoldProducts();
	
	List<Product> getNotBuyProducts();
	
	int updateProduct(List<SoldItemVo> list);
	
	int publicProduct(PublicProductVo productVo);
}
