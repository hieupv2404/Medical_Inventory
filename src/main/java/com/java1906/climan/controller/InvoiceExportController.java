package com.java1906.climan.controller;

import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceCustomer;
import com.java1906.climan.data.model.InvoiceExport;
import com.java1906.climan.data.model.InvoiceSupplier;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.IInvoiceExportService;
import com.java1906.climan.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceExportController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IInvoiceExportService invoiceExportService;

    @GetMapping("/invoiceExport")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<List<InvoiceCustomer>> showInvoiceList(Model model) {
        List<InvoiceCustomer> invoiceExport = invoiceService.findAllInvoiceExport();
        model.addAttribute("titlePage","Invoice Export");
        model.addAttribute("InvoiceCustomerList",invoiceExport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get  by id
    @GetMapping("/invoiceExport/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<InvoiceExport> getInvoiceById(Model model,@PathVariable("id") Integer id) {
        InvoiceExport invoiceExport = invoiceExportService.findById(id).get();
        model.addAttribute("titlePage","Invoice Export");
        model.addAttribute("InvoiceCustomerList",invoiceExport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

  // create invoice
    @PostMapping("/invoiceExport/")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice){
        invoice.setType(2);
        return new ResponseEntity<>(invoiceService.save(invoice),HttpStatus.CREATED);
    }

    //update invoice
    @PutMapping("/invoiceExport/{invoiceId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("invoiceId") int invoiceId, @RequestBody Invoice invoice){
        return new ResponseEntity<>(invoiceService.update(invoiceId,invoice),HttpStatus.CREATED);
    }
    //
    @DeleteMapping("/{invoiceExportId}")
    public ResponseEntity<String> deleteInvoice( @PathVariable("invoiceExportId") int invoiceId){
      invoiceService.delete(invoiceId);
        return new ResponseEntity<>("Delete Ok",HttpStatus.OK);
    }

}
