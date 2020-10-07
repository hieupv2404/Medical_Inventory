//package com.java1906.climan.data.repo;
//
//import com.java1906.climan.data.model.CategoryValue;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface CategoryValueByProduct extends JpaRepository<CategoryValue, Integer> {
//    @Query(value="SELECT cv.* FROM category_value cv WHERE cv.category_id = 1\n" +
//            "    AND cv.id IN (SELECT pc.category_value_id FROM product_info_category_value pc WHERE pc.product_info_id = ?1) ",nativeQuery=true)
//    CategoryValue findCategoryValueByFate(@Param("productId") Integer productId);
//
//    @Query(value="SELECT cv.* FROM category_value cv WHERE cv.category_id = 2\n" +
//            "    AND cv.id IN (SELECT pc.category_value_id FROM product_info_category_value pc WHERE pc.product_info_id = ?1)",nativeQuery=true)
//    CategoryValue findCategoryValueByProperty(@Param("productId") Integer productId);
//
//
//}
