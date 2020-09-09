package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.Customer;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    //Get all customer
    @GetMapping("/customer")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<Customer>> showCustomerList(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("titlePage","Customer List");
        model.addAttribute("customerList",customerList);
        return new ResponseEntity<>(customerList,HttpStatus.OK);

    }

    //Get customer by id
    @GetMapping("/customer/{customerId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getCustomerById(Model model,@PathVariable("customerId") int customerId) {
        Customer customer = customerService.findById(customerId).get();
        model.addAttribute("titlePage","Customer List");
        model.addAttribute("customer",customer);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    // Create customer
    @PostMapping("/customer")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        return new ResponseEntity<Customer>(customerService.save(customer),HttpStatus.CREATED);
    }
    // Update customer
    @PutMapping("/customer/{customerId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") Integer customerId,
                                                 @RequestBody Customer customer) throws Exception {
        customerService.update(customerId,customer);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }

    // Delete customer
    @DeleteMapping("/customer/{customerId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        customerService.delete(customerId);
        return new ResponseEntity<String>("delete_ok",HttpStatus.OK);
    }

}
