package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.CategoryValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryValueRepository extends JpaRepository<CategoryValue, Integer> {
    Optional<CategoryValue> findByCategory_id(Integer categoryId);

   @Query(value="select * from category_value cv where cv.name like %:name% ",nativeQuery=true)
    List<CategoryValue> findCategoryValueByName(@Param("name") String categoryValueName);

}
