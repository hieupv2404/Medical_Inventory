package com.java1906.climan.controller;

import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductInStockController {

    @Autowired
    private ProductInStockService productInStockService;

    @GetMapping("/product-in-stock/list")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getProductInStock() {
        return new ResponseEntity<>(productInStockService.findAll(), HttpStatus.OK);
    }
}
