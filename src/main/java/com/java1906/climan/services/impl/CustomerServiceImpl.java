package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Customer;
import com.java1906.climan.data.repo.CustomerRepository;
import com.java1906.climan.data.repo.CustomerRepository;
import com.java1906.climan.services.ICustomerService;
import com.java1906.climan.services.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll(String name, String email) {
        if(name != null && !StringUtils.isEmpty(name) || email != null && !StringUtils.isEmpty(email))
        {
            return customerRepository.findCustomerByProperty(name,email);
        }
        else {
            return customerRepository.findAll();
        }
    }

    @Override
    public Optional<Customer> findById(int customerId) {
        if (!customerRepository.existsById(customerId)) {
            try {
                throw new ResourceNotFoundException("Category with"+ customerId+ "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return customerRepository.findById(customerId);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(int customerId, Customer customer) throws Exception {
        if (!customerRepository.existsById(customerId)) {
            try {
                throw new ResourceNotFoundException("Author with id " + customerId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Optional<Customer> customerRepositoryById =customerRepository.findById(customerId);

        if (!customerRepositoryById.isPresent()) {
            try {
                throw new ResourceNotFoundException("Author with id " + customerId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Customer customer1 = customerRepositoryById.get();
        customer1.setName(customer.getName());
        customer1.setAddress(customer.getAddress());
        customer1.setEmail(customer.getNumberPhone());
        customer1.setNumberPhone(customer.getNumberPhone());
        return customerRepository.save(customer1);
    }

    @Override
    public void delete(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            try {
                throw new ResourceNotFoundException("Author with id " + customerId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findCustomerNativeQueryByName(name);
    }
}

