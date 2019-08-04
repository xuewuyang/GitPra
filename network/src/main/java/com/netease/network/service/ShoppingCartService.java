package com.netease.network.service;

import com.netease.network.dto.SoldItemDo;
import com.netease.network.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getShoppingCarts();

    Boolean insert(ShoppingCart shoppingCart);

    Boolean deleteShoppingCartByProductId(Integer productId);

    Boolean deleteShoppingCartOfSold(List<SoldItemDo> list);
}
