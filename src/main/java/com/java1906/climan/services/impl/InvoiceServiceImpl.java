package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.services.IInvoiceService;
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


    @Override
    public List<Invoice> finAllInvoice() {
        return invoiceRepository.findAll();
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
        invoice.setCreatedDate(new Date());
        invoice.setUpdatedDate(new Date());
        
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
        invoice1.setCreatedDate(invoice.getCreatedDate());
        invoice1.setUpdatedDate(new Date());


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
}
