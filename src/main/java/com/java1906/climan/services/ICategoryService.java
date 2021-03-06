package com.java1906.climan.services;

import com.java1906.climan.data.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll(String categoryName);

    Optional<Category> findById(Integer id);

    Category save(Category category);

    Category update(int categoryId , Category category) throws Exception;

    void delete(Integer categoryId);


}
