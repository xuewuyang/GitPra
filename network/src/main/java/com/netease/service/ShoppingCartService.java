package com.netease.service;

import java.util.List;

import com.netease.entity.ShoppingCart;
import com.netease.vo.SoldItemVo;

public interface ShoppingCartService {

	List<ShoppingCart> getShoppingCarts();
	
	Boolean addShoppingCart(ShoppingCart shoppingCart);
	
	Boolean deleteShoppingCartByProductId(Integer productId);
	
	Boolean deleteShoppingCartOfSold(List<SoldItemVo> list);
}
