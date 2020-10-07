package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.ProductInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductInStockRepository extends JpaRepository<ProductInStock,Integer> {
    @Query(value = "select * from product_in_stock ps where ps.product_id = ?1",nativeQuery=true)
    ProductInStock findByProduct(@Param("productId") Integer productId);
}
