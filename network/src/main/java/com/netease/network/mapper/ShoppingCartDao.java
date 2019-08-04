package com.netease.network.mapper;

import com.netease.network.dto.SoldItemDo;
import com.netease.network.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartDao {

    List<ShoppingCart> getShoppingCarts();

    Integer insert(ShoppingCart shoppingCart);

    Integer deleteShoppingCartByProductId(int productId);

    Integer deleteShoppingCartOfSold(List<SoldItemDo> list);
}
