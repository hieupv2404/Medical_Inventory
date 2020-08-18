package com.java1906.climan.services;


import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.ProductInfo;

import java.util.Optional;

public interface IProducInfoService {
    Iterable<ProductInfo> findAll();

    Optional<ProductInfo> findById(int id);

     ProductInfo save(ProductInfo productInfo);

    public ProductInfo update(ProductInfo product);

    void delete(int productId);
//    void save(ProductInfo productInfo);

}
