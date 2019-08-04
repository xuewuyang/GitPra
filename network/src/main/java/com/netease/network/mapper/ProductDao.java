package com.netease.network.mapper;

import com.netease.network.dto.PublicProductDo;
import com.netease.network.dto.SoldItemDo;
import com.netease.network.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {

    List<Product> findAll();
    Product findProductById(Integer id);
    Integer deleteProductById(Integer id);
    List<Product> findSoldProducts();
    List<Product> findNotBuyProducts();
    List<Product> searchProducts(String searchInfo);
    Integer updateProduct(List<SoldItemDo> list);
    Integer publicProduct(PublicProductDo productDo);
}
