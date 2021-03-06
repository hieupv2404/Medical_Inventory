package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceCustomer;
import com.java1906.climan.data.model.InvoiceSupplier;
import com.java1906.climan.data.repo.InvoiceCustomerRepository;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.InvoiceSupplierRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.dto.InvoiceDTO;
import com.java1906.climan.services.IInvoiceService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class InvoiceServiceImpl implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private InvoiceSupplierRepository invoiceSupplierRepository;

    @Autowired
    private InvoiceCustomerRepository invoiceCustomerRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<InvoiceSupplier> findAllInvoiceImport(String name, String code, String email) {
        if(name != null && !StringUtils.isEmpty(name) || email != null && !StringUtils.isEmpty(email) || code != null && !StringUtils.isEmpty(code))
        {
            return invoiceSupplierRepository.findInvoiceImport(name, code, email);
        }
        else {
            return invoiceSupplierRepository.findAllInvoiceImport();
        }
    }

    @Override
    public List<InvoiceCustomer> findAllInvoiceExport(String name, String code, String email) {
        if(name != null && !StringUtils.isEmpty(name) || email != null && !StringUtils.isEmpty(email) || code != null && !StringUtils.isEmpty(code))
        {
            return invoiceCustomerRepository.findInvoiceExport(name, code, email);
        }
        else {
            return invoiceCustomerRepository.findAllInvoiceExport();
        }
    }

    @Override
    public Optional<Invoice> findById(Integer invoiceId) {
        if(!invoiceRepository.existsById(invoiceId)){
            try{
                throw new ResourceNotFoundException("Invoice with"+invoiceId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        return invoiceRepository.findById(invoiceId);

    }

    @Override
    public Invoice save(Invoice invoice) {
        invoice.setActiveFlag(1);
        invoice.setCreatedDate(new Date());
        invoice.setUpdatedDate(new Date());
        invoice.setInTotal(0);
        invoice.setOutTotal(0);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice update(int invoiceId, Invoice invoice) {
        if(!invoiceRepository.existsById(invoiceId)){
            try {
                throw new ResourceNotFoundException("Invoice with id "+invoiceId + "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Optional<Invoice> invoiceById =invoiceRepository.findById(invoiceId);
        if(!invoiceById.isPresent()){
            try {
                throw new ResourceNotFoundException("Invoice with"+invoiceId + " not fount");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Invoice invoice1 =invoiceById.get();
        invoice1.setUpdatedDate(new Date());
        invoice1.setCustomerId(invoice.getCustomerId());
        invoice1.setSupplierId(invoice.getSupplierId());
        return invoiceRepository.save(invoice1);
    }

    @Override
    public void delete(int invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            try {
                throw new ResourceNotFoundException("Invoice with id " + invoiceId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public InvoiceSupplier findImportById(Integer id) {
        return invoiceSupplierRepository.findImportById(id);
    }

    @Override
    public InvoiceCustomer findExportById(Integer id) {
        return invoiceCustomerRepository.findExportById(id);
    }
}
