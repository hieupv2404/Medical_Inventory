package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Customer;
import com.java1906.climan.data.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {
    @Query(value="select * from product_info p where p.name like %:productName%",nativeQuery=true)
    List<ProductInfo> findProductInfoByName(@Param(value="productName") String productName);
}
