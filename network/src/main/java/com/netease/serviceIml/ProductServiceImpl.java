package com.netease.serviceIml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.dao.ProductDao;
import com.netease.dao.ShoppingCartDao;
import com.netease.entity.Product;
import com.netease.service.ProductService;
import com.netease.vo.PublicProductVo;
import com.netease.vo.SoldItemVo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}
	
	@Transactional
	@Override
	public Boolean deleteProductById(int id) {
		shoppingCartDao.deleteShoppingCartByProductId(id);
		return productDao.deleteProductById(id) > 0;
	}
	
	@Override
	public List<Product> getSoldProducts() {
		return productDao.getSoldProducts();
	}
	
	@Override
	public List<Product> getNotBuyProducts() {
		return productDao.getNotBuyProducts();
	}
	
	@Override
	@Transactional
	public Boolean updateProduct(List<SoldItemVo> list) {
		return productDao.updateProduct(list) > 0 && shoppingCartDao.deleteShoppingCartOfSold(list) > 0;
	}
	
	@Override
	public Boolean publicProduct(PublicProductVo productVo) {
		return productDao.publicProduct(productVo) > 0;
	}
}
