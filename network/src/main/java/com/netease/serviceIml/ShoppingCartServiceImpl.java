package com.netease.serviceIml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.ShoppingCartDao;
import com.netease.entity.ShoppingCart;
import com.netease.service.ShoppingCartService;
import com.netease.vo.SoldItemVo;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Override
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCartDao.getShoppingCarts();
	}
	
	@Override
	public Boolean addShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartDao.insert(shoppingCart) > 0;
	}
	
	@Override
	public Boolean deleteShoppingCartByProductId(Integer productId) {
		return shoppingCartDao.deleteShoppingCartByProductId(productId) > 0;
	}

	@Override
	public Boolean deleteShoppingCartOfSold(List<SoldItemVo> list) {
		return shoppingCartDao.deleteShoppingCartOfSold(list) > 0;
	}
}
