package com.java1906.climan.controller;

import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.data.model.InvoiceSupplier;
import com.java1906.climan.dto.InvoiceDTO;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.IInvoiceItemService;
import com.java1906.climan.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceImportController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IInvoiceItemService invoiceItemService;

    @GetMapping("/invoiceImport")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<List<InvoiceSupplier>> showInvoiceList() {
        return new ResponseEntity<>(invoiceService.findAllInvoiceImport(), HttpStatus.OK);
    }

    //get  byid
    @GetMapping("/invoiceImport/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getInvoiceById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(invoiceService.findById(id),HttpStatus.OK);
    }
  // create invoice
    @PostMapping("/invoiceImport/")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> createInvoce(@RequestBody Invoice invoice){
        invoice.setType(1);
        return new ResponseEntity<>(invoiceService.save(invoice),HttpStatus.CREATED);
    }

    //update invoice
    @PutMapping("/invoiceImport/{invoiceId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("invoiceId") int invoiceId, @RequestBody Invoice invoice){
        return new ResponseEntity<>(invoiceService.update(invoiceId,invoice),HttpStatus.CREATED);
    }
    //
    @DeleteMapping("/{invoiceImportId}")
    public ResponseEntity<String> deleteInvoice( @PathVariable("invoiceImportId") int invoiceId){
      invoiceService.delete(invoiceId);
        return new ResponseEntity<>("Delete Ok",HttpStatus.OK);
    }

}
