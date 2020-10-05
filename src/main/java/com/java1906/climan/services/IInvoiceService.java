package com.java1906.climan.services;



import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceCustomer;
import com.java1906.climan.data.model.InvoiceSupplier;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
        List<Invoice> findAllInvoice();
        List<InvoiceSupplier> findAllInvoiceImport(String name, String code, String email);
        List<InvoiceCustomer> findAllInvoiceExport(String name, String code, String email);

        Optional<Invoice> findById(Integer invoiceId);

        Invoice save(Invoice invoice);

         Invoice update(int invoiceId, Invoice invoice);

        void delete(int invoiceId);
//    void save(ProductInfo productInfo);
}
