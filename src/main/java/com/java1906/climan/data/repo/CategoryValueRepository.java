package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.CategoryValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryValueRepository extends JpaRepository<CategoryValue, Integer> {
 Optional<CategoryValue> findByCategory_id(Integer categoryId);
}
