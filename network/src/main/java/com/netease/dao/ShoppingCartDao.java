package com.netease.dao;

import java.util.List;

import com.netease.entity.ShoppingCart;
import com.netease.vo.SoldItemVo;

public interface ShoppingCartDao {

	List<ShoppingCart> getShoppingCarts();
	
	int insert(ShoppingCart shoppingCart);
	
	int deleteShoppingCartByProductId(int productId);
	
	int deleteShoppingCartOfSold(List<SoldItemVo> list);
}
