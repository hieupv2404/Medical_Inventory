package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.ProductInfoCategoryValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductInfoCategoryValueRepository extends JpaRepository<ProductInfoCategoryValue, Integer> {
    @Query(value="select  * from ProductInfoCategoryValue p where p.productInfoId=?1 and p.categoryValueId=?2",nativeQuery=true)
    ProductInfoCategoryValue findByProductIdAndCategoryValueId(Integer productInfoId,Integer categoryValueId);
}
