package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.ProductInStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInStockRepository extends JpaRepository<ProductInStock,Integer> {
}
