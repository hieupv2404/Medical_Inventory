package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select * from Customer s where s.name = ?1", nativeQuery = true)
    Customer findCustomerNativeQueryByName(String name);
}
