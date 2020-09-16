package com.java1906.climan.services;

import com.java1906.climan.data.model.InvoiceExport;

import java.util.List;
import java.util.Optional;

public interface IInvoiceExportService {

    List<InvoiceExport> findAll();

    Optional<InvoiceExport> findById(int id);

    List<InvoiceExport> findByInvoiceId(int invoiceId);

    List<InvoiceExport> findByCustomerId(int supplierId);

    InvoiceExport save(InvoiceExport invoiceExport, int invoiceId, int supplierId);

    InvoiceExport update(int invoiceId, int supplierId, InvoiceExport invoiceExport, int invoiceExportId) throws Exception;

    void delete(int invoiceExportId);



}
