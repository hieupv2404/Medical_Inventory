package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceImport;
import com.java1906.climan.data.model.Supplier;
import com.java1906.climan.data.repo.InvoiceImportRepository;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.SupplierRepository;
import com.java1906.climan.services.IInvoiceImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class InvoiceImportServiceImpl implements IInvoiceImportService {

    @Autowired
    private InvoiceImportRepository invoiceImportRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public List<InvoiceImport> findAll() {
        return invoiceImportRepository.findAll();
    }

    @Override
    public Optional<InvoiceImport> findById(int id) {
        if(!invoiceImportRepository.existsById(id)){
            try{
                throw new ResourceNotFoundException("Invoice Import with"+id+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        return invoiceImportRepository.findById(id);
    }

    @Override
    public List<InvoiceImport> findByInvoiceId(int invoiceId) {
        if(!invoiceRepository.existsById(invoiceId)){
            try{
                throw new ResourceNotFoundException("Invoice with"+invoiceId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        List<InvoiceImport> invoiceImportList = invoiceImportRepository.findAll();
        List<InvoiceImport> invoiceImports = new ArrayList<>();
        for(InvoiceImport invoiceImport : invoiceImportList)
        {
            if(invoiceImport.getInvoice() == invoice)
                invoiceImports.add(invoiceImport);
        }
        return invoiceImports;
    }

    @Override
    public List<InvoiceImport> findBySupplierId(int supplierId) {
        if(!supplierRepository.existsById(supplierId)){
            try{
                throw new ResourceNotFoundException("Supplier with"+supplierId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        List<InvoiceImport> invoiceImports = new ArrayList<>();
        List<InvoiceImport> invoiceImportList = invoiceImportRepository.findAll();
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        Supplier supplier = supplierOptional.get();
        for (InvoiceImport invoiceImport : invoiceImportList)
        {
            if(invoiceImport.getSupplier() == supplier)
            {
                invoiceImports.add(invoiceImport);
            }
        }
        return invoiceImports;
    }

    @Override
    public InvoiceImport save(InvoiceImport invoiceImport, int invoiceId, int supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        Supplier supplier = supplierOptional.get();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        invoiceImport.setInvoice(invoice);
        invoiceImport.setSupplier(supplier);
        return invoiceImportRepository.save(invoiceImport);
    }

    @Override
    public InvoiceImport update(int invoiceId, int supplierId, InvoiceImport invoiceImport, int invoiceImportId) throws Exception {
        if(!invoiceImportRepository.existsById(invoiceImportId)){
            try{
                throw new ResourceNotFoundException("Invoie Import with"+invoiceImportId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        Supplier supplier = supplierOptional.get();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        invoiceImport.setInvoice(invoice);
        invoiceImport.setSupplier(supplier);
        return invoiceImportRepository.save(invoiceImport);
    }

    @Override
    public void delete(int invoiceImportId) {
        if (!invoiceImportRepository.existsById(invoiceImportId)) {
            try {
                throw new ResourceNotFoundException("Invoice Import with id " + invoiceImportId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        invoiceImportRepository.deleteById(invoiceImportId);
    }

}
