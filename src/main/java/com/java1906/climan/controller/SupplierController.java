package com.java1906.climan.controller;

import com.java1906.climan.data.model.Supplier;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    //Get all supplier
    @GetMapping("/supplier")
    @HasRole({"STAFF", "ADMIN"})
    public String showSupplierList() {
//         return new ResponseEntity<>(supplierService.getAll(),HttpStatus.OK);
        return "calendar";
    }

    //Get supplier by id
    @GetMapping("/supplier/{supplierId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getSupplierById(@PathVariable("supplierId") int supplierId) {
    return new ResponseEntity<>(supplierService.findById(supplierId),HttpStatus.OK);
    }

    // Create supplier
    @PostMapping("/supplier")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {

        return new ResponseEntity<Supplier>(supplierService.save(supplier),HttpStatus.CREATED);
    }
    // Update supplier
    @PutMapping("/supplier/{supplierId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("supplierId") Integer supplierId,
                                                 @RequestBody Supplier supplier) throws Exception {
            return new ResponseEntity<Supplier>(supplierService.update(supplierId,supplier),HttpStatus.NOT_FOUND);
    }

    // Delete supplier
    @DeleteMapping("/supplier/{supplierId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> deleteSupplier(@PathVariable("supplierId") Integer supplierId) {
        supplierService.delete(supplierId);
        return new ResponseEntity<String>("delete_ok",HttpStatus.OK);
    }

}
