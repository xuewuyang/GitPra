package com.netease.network.service.serviceImpl;

import com.netease.network.dto.SoldItemDo;
import com.netease.network.entity.ShoppingCart;
import com.netease.network.mapper.ShoppingCartDao;
import com.netease.network.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCartDao.getShoppingCarts();
    }

    @Override
    public Boolean insert(ShoppingCart shoppingCart) {
        return shoppingCartDao.insert(shoppingCart) > 0;
    }

    @Override
    public Boolean deleteShoppingCartByProductId(Integer productId) {
        return shoppingCartDao.deleteShoppingCartByProductId(productId) > 0;
    }

    @Override
    public Boolean deleteShoppingCartOfSold(List<SoldItemDo> list) {
        return shoppingCartDao.deleteShoppingCartOfSold(list) > 0;
    }
}
