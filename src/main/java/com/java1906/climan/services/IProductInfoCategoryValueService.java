package com.java1906.climan.services;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.ProductInfoCategoryValue;

import java.util.List;
import java.util.Optional;

public interface IProductInfoCategoryValueService {
    List<ProductInfoCategoryValue> findAll();

    Optional<ProductInfoCategoryValue> findById(Integer id);

    ProductInfoCategoryValue save(ProductInfoCategoryValue category);

//    ProductInfoCategoryValue update(int categoryId , Category category) throws Exception;

    void delete(Integer categoryId);

    ProductInfoCategoryValue findByProductIdAndCategoryValueId(Integer productInfoId,Integer categoryValueId);


}
