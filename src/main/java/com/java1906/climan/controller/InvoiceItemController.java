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
    @GetMapping("/invoices/{id}/invoices-item")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<InvoiceItem>> showInvoiceItemList(@PathVariable("id") int invoiceId) {
        return new ResponseEntity <List<InvoiceItem>>(invoiceItemService.findAllByInvoiceId(invoiceId), HttpStatus.OK);
    }

    //GET ById
    @GetMapping("/invoices/invoices-item/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getInvoiceItemById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(invoiceItemService.findById(id),HttpStatus.OK);
    }

    // Create InvoiceItem
    @PostMapping("/invoices/invoices-item/")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<InvoiceItem> createInvoiceItem(
            @RequestParam int invoiceId,
            @RequestParam int productId,
            @RequestParam int unitId,
            @RequestBody InvoiceItem InvoiceItem)   {
        return new ResponseEntity<>(invoiceItemService.save(invoiceId,InvoiceItem, unitId, productId),HttpStatus.OK);
    }

    // Update category value
    @PutMapping("/invoices/invoices-item/{InvoiceItemId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@PathVariable("InvoiceItemId") Integer InvoiceItemId,
                                                              @RequestParam int productId, @RequestParam int unitId,
                                                         @RequestBody InvoiceItem InvoiceItem) {
        return new ResponseEntity<InvoiceItem>(invoiceItemService.update(InvoiceItemId,productId,unitId, InvoiceItem), HttpStatus.OK);
    }

    // Delete category value
    @DeleteMapping("/invoices/invoices-item/{InvoiceItemId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> deleteInvoiceItem(@PathVariable("InvoiceItemId") Integer InvoiceItemId) {
        invoiceItemService.delete(InvoiceItemId);
        return new ResponseEntity<String>("Delete Ok",HttpStatus.OK);
    }
    
    
}
