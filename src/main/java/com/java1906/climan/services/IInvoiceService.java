package com.java1906.climan.services;



import com.java1906.climan.data.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
        List<Invoice> finAllInvoice();

        Optional<Invoice> findById(Integer invoiceId);

        Invoice save(Invoice invoice);

         Invoice update(int invoiceId, Invoice invoice);

        void delete(int invoiceId);
//    void save(ProductInfo productInfo);
}
