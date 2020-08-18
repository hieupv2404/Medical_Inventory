package com.java1906.climan.services;

import com.java1906.climan.data.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    Optional<Category> findById(Integer id);

    void save(Category category);

    void update(int categoryId , Category category) throws Exception;

    void delete(Integer categoryId);


}
