package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceExport;
import com.java1906.climan.data.model.Customer;
import com.java1906.climan.data.repo.InvoiceExportRepository;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.CustomerRepository;
import com.java1906.climan.services.IInvoiceExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvoiceExportServiceImpl implements IInvoiceExportService {

    @Autowired
    private InvoiceExportRepository invoiceExportRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<InvoiceExport> findAll() {
        return invoiceExportRepository.findAll();
    }

    @Override
    public Optional<InvoiceExport> findById(int id) {
        if(!invoiceExportRepository.existsById(id)){
            try{
                throw new ResourceNotFoundException("Invoice Import with"+id+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        return invoiceExportRepository.findById(id);
    }

    @Override
    public List<InvoiceExport> findByInvoiceId(int invoiceId) {
        if(!invoiceRepository.existsById(invoiceId)){
            try{
                throw new ResourceNotFoundException("Invoice with"+invoiceId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        List<InvoiceExport> invoiceImportList = invoiceExportRepository.findAll();
        List<InvoiceExport> invoiceImports = new ArrayList<>();
        for(InvoiceExport invoiceImport : invoiceImportList)
        {
            if(invoiceImport.getInvoice() == invoice)
                invoiceImports.add(invoiceImport);
        }
        return invoiceImports;
    }

    @Override
    public List<InvoiceExport> findByCustomerId(int supplierId) {
        if(!customerRepository.existsById(supplierId)){
            try{
                throw new ResourceNotFoundException("Customer with"+supplierId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        List<InvoiceExport> invoiceImports = new ArrayList<>();
        List<InvoiceExport> invoiceImportList = invoiceExportRepository.findAll();
        Optional<Customer> supplierOptional = customerRepository.findById(supplierId);
        Customer supplier = supplierOptional.get();
        for (InvoiceExport invoiceImport : invoiceImportList)
        {
            if(invoiceImport.getCustomer() == supplier)
            {
                invoiceImports.add(invoiceImport);
            }
        }
        return invoiceImports;
    }

    @Override
    public InvoiceExport save(InvoiceExport invoiceImport, int invoiceId, int supplierId) {
        Optional<Customer> supplierOptional = customerRepository.findById(supplierId);
        Customer supplier = supplierOptional.get();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        invoiceImport.setInvoice(invoice);
        invoiceImport.setCustomer(supplier);
        return invoiceExportRepository.save(invoiceImport);
    }

    @Override
    public InvoiceExport update(int invoiceId, int supplierId, InvoiceExport invoiceImport, int invoiceImportId) throws Exception {
        if(!invoiceExportRepository.existsById(invoiceImportId)){
            try{
                throw new ResourceNotFoundException("Invoie Import with"+invoiceImportId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        Optional<Customer> supplierOptional = customerRepository.findById(supplierId);
        Customer supplier = supplierOptional.get();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        invoiceImport.setInvoice(invoice);
        invoiceImport.setCustomer(supplier);
        return invoiceExportRepository.save(invoiceImport);
    }

    @Override
    public void delete(int invoiceImportId) {
        if (!invoiceExportRepository.existsById(invoiceImportId)) {
            try {
                throw new ResourceNotFoundException("Invoice Import with id " + invoiceImportId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        invoiceExportRepository.deleteById(invoiceImportId);
    }

}
