package com.java1906.climan.services;


import com.java1906.climan.data.model.CategoryValue;

import java.util.List;
import java.util.Optional;

public interface ICategoryValueService {

    List<CategoryValue> findAll(String categoryValueName);
    Optional<CategoryValue> findById(Integer categoryValueId);
    CategoryValue save( int categoryId,CategoryValue categoryValue);
    CategoryValue update(int categoryValueId, CategoryValue categoryValue);
    void delete(Integer categoryValueId);
    Optional<CategoryValue> findByCategory(Integer categoryId);
    List<CategoryValue> findAllByNameContatining(String name); // phần này tìm kiếm
    CategoryValue findCategoryValueByFate(Integer productId);
    CategoryValue findCategoryValueByProperty(Integer productId);
    void save(CategoryValue categoryValue);
}
