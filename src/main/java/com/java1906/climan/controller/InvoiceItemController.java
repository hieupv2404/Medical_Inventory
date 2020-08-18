package com.java1906.climan.controller;

import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.IInvoiceItemService;
import com.java1906.climan.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceItemController {
    @Autowired
    private IInvoiceItemService invoiceItemService;

    @Autowired
    private IInvoiceService invoiceService;

    //Get all item
    @GetMapping("/invoice/{id}/invoiceItem")
    @CrossOrigin(origins = "http://localhost:4200")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<InvoiceItem>> showInvoiceItemList(@PathVariable("id") int invoiceId) {
        return new ResponseEntity <List<InvoiceItem>>(invoiceItemService.findAllByInvoiceId(invoiceId), HttpStatus.OK);
    }

    //GET ById
    @GetMapping("invoice/invoiceItem/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getInvoiceItemById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(invoiceItemService.findById(id),HttpStatus.OK);
    }

    // Create InvoiceItem
    @PostMapping("/invoice/invoiceItem/")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<InvoiceItem> createInvoiceItem(
            @RequestParam int invoiceId,
            @RequestParam int productId,
            @RequestBody InvoiceItem InvoiceItem)   {
        return new ResponseEntity<>(invoiceItemService.save(invoiceId,InvoiceItem, productId),HttpStatus.OK);
    }

    // Update category value
    @PutMapping("/invoice/invoiceItem/{InvoiceItemId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@PathVariable("InvoiceItemId") Integer InvoiceItemId,
                                                              @RequestBody InvoiceItem InvoiceItem) {
        return new ResponseEntity<InvoiceItem>(invoiceItemService.update(InvoiceItemId,InvoiceItem), HttpStatus.OK);
    }

    // Delete category value
    @DeleteMapping("/invoice/invoiceItem/{InvoiceItemId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> deleteInvoiceItem(@PathVariable("InvoiceItemId") Integer InvoiceItemId) {
        invoiceItemService.delete(InvoiceItemId);
        return new ResponseEntity<String>("Delete Ok",HttpStatus.OK);
    }
    
    
}
