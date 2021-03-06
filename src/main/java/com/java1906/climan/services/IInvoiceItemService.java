package com.java1906.climan.services;

import com.java1906.climan.data.model.InvoiceItem;

import java.util.List;
import java.util.Optional;

public interface IInvoiceItemService {
    List<InvoiceItem> findAll();
    Optional<InvoiceItem> findById(Integer invoiceItemId);
    InvoiceItem save( int invoiceId,InvoiceItem invoiceItem,Integer unitId, int productId);
    InvoiceItem update(Integer invoiceId, Integer productId,Integer unitId, InvoiceItem invoiceItem);
    void delete(Integer invoiceItemId);
    List<InvoiceItem> findAllByInvoiceId(int invoiceId);

}
