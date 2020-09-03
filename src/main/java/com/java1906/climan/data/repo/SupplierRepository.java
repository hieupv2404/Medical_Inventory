package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Customer;
import com.java1906.climan.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Query(value = "select * from Supplier s where s.name = ?1", nativeQuery = true)
    Supplier findSupplierNativeQueryByName(String name);
}
