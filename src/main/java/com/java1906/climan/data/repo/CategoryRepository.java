package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value="select * from category c where c.name like %:name%",nativeQuery=true)
    List<Category> findByNameCategory(@Param("name") String categoryName);
}
