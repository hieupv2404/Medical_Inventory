package com.java1906.climan.services;


import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.ProductInfo;

import java.util.List;
import java.util.Optional;

public interface IProducInfoService {
    List<ProductInfo> findAll(String productName, String categoryValue);

    Optional<ProductInfo> findById(int id);

     ProductInfo save(ProductInfo productInfo);

    public ProductInfo update(ProductInfo product);

    void delete(int productId);
//    void save(ProductInfo productInfo);

}
