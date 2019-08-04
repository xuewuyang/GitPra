package com.netease.network.service;

import com.netease.network.dto.PublicProductDo;
import com.netease.network.dto.SoldItemDo;
import com.netease.network.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findProductById(Integer id);
    Boolean deleteProductById(Integer id);
    List<Product> findSoldProducts();
    List<Product> findNotBuyProducts();
    List<Product> searchProducts(String searchInfo);
    Boolean updateProduct(List<SoldItemDo> list);
    Boolean publicProduct(PublicProductDo productDo);
}
