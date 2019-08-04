package com.netease.network.service.serviceImpl;

import com.netease.network.dto.PublicProductDo;
import com.netease.network.dto.SoldItemDo;
import com.netease.network.entity.Product;
import com.netease.network.mapper.ProductDao;
import com.netease.network.mapper.ShoppingCartDao;
import com.netease.network.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findProductById(Integer id) {
        return productDao.findProductById(id);
    }

    @Transactional
    @Override
    public Boolean deleteProductById(Integer id) {
        shoppingCartDao.deleteShoppingCartByProductId(id);
        return productDao.deleteProductById(id) > 0;
    }

    @Override
    public List<Product> findSoldProducts() {
        return productDao.findSoldProducts();
    }

    @Override
    public List<Product> findNotBuyProducts() {
        return productDao.findNotBuyProducts();
    }

    public List<Product> searchProducts(String searchInfo) {
        return productDao.searchProducts(searchInfo);
    }

    @Override
    @Transactional
    public Boolean updateProduct(List<SoldItemDo> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SoldItemDo soldItemDo : list) {
            soldItemDo.setBuyTime(simpleDateFormat.format(System.currentTimeMillis()));
            System.out.println(soldItemDo.getId());
        }
        return productDao.updateProduct(list) > 0 && shoppingCartDao.deleteShoppingCartOfSold(list) > 0;
    }

    @Override
    public Boolean publicProduct(PublicProductDo productDo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        productDo.setBuyTime(simpleDateFormat.format(System.currentTimeMillis()));
        return productDao.publicProduct(productDo) > 0;
    }
}
