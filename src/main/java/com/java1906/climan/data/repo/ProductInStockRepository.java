package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.ProductInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductInStockRepository extends JpaRepository<ProductInStock,Integer> {
    @Query(value = "select * from ProductInStock where ProductInStock .productInfo.name like %:productName% " +
                                    "and ProductInStock .productInfo.categoryValues.")
    ProductInStock findByProperty(@Param("productName") String productName, @Param("categoryValue") String categoryValue);
}
