package com.java1906.climan.services;
import com.java1906.climan.data.model.Customer;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(int supplierId);

    Customer save(Customer customer);

    Customer update(int customerId , Customer customer) throws Exception;

    void delete(Integer customerId);

    Customer findByName(String name);
}
