package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Customer;
import com.java1906.climan.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Query(value = "select * from supplier s where s.name = ?1", nativeQuery = true)
    Supplier findSupplierNativeQueryByName(String name);

    @Query(value="select * from supplier s where s.name like %:name% and s.email like %:email%",nativeQuery=true)
    List<Supplier> findSupplierByProperty(@Param(value="name") String name, @Param(value="email") String email);
}
