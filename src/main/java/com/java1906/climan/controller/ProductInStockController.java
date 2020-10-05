package com.java1906.climan.controller;

import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInStockController {

    @Autowired
    private ProductInStockService productInStockService;

    @GetMapping("/product-in-stock")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getProductInStock(@RequestParam("productName") String productName,
                                                                                                @RequestParam("categoryValue") String categoryValue) {
        return new ResponseEntity<>(productInStockService.findAll(productName, categoryValue), HttpStatus.OK);
    }
}
