package com.java1906.climan.services;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.InvoiceImport;

import java.util.List;
import java.util.Optional;

public interface IInvoiceImportService {

    List<InvoiceImport> findAll();

    Optional<InvoiceImport> findById(int id);

    List<InvoiceImport> findByInvoiceId(int invoiceId);

    List<InvoiceImport> findBySupplierId(int supplierId);

    InvoiceImport save(InvoiceImport invoiceImport, int invoiceId, int supplierId);

    InvoiceImport update(int invoiceId, int supplierId, InvoiceImport invoiceImport, int invoiceImportId) throws Exception;

    void delete(int invoiceImportId);



}
