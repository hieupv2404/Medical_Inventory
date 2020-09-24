package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select * from customer s where s.name = ?1", nativeQuery = true)
    Customer findCustomerNativeQueryByName(String name);

    @Query(value="select * from customer s where s.name like %:name% and s.email like %:email%",nativeQuery=true)
    List<Customer> findCustomerByProperty(@Param(value="name") String name, @Param(value="email") String email);
}
