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
        List<InvoiceExport> invoiceExportList = invoiceExportRepository.findAll();
        List<InvoiceExport> invoiceExports = new ArrayList<>();
        for(InvoiceExport invoiceExport : invoiceExportList)
        {
            if(invoiceExport.getInvoice() == invoice)
                invoiceExports.add(invoiceExport);
        }
        return invoiceExports;
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
        List<InvoiceExport> invoiceExports = new ArrayList<>();
        List<InvoiceExport> invoiceExportList = invoiceExportRepository.findAll();
        Optional<Customer> supplierOptional = customerRepository.findById(supplierId);
        Customer customer = supplierOptional.get();
        for (InvoiceExport invoiceExport : invoiceExportList)
        {
            if(invoiceExport.getCustomer() == customer)
            {
                invoiceExports.add(invoiceExport);
            }
        }
        return invoiceExports;
    }

    @Override
    public InvoiceExport save(InvoiceExport invoiceImport, int invoiceId, int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        invoiceImport.setInvoice(invoice);
        invoiceImport.setCustomer(customer);
        return invoiceExportRepository.save(invoiceImport);
    }

    @Override
    public InvoiceExport update(int invoiceId, int customerId, InvoiceExport invoiceExport, int invoiceExportId) throws Exception {
        if(!invoiceExportRepository.existsById(invoiceExportId)){
            try{
                throw new ResourceNotFoundException("Invoie Export with"+invoiceExportId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        invoiceExport.setInvoice(invoice);
        invoiceExport.setCustomer(customer);
        return invoiceExportRepository.save(invoiceExport);
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
