package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceSupplier;
import com.java1906.climan.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

//    @Query(value = "select * from Invoice inv where inv.type = 1", nativeQuery = true)
//    @Query( value="SELECT i.id, i.inTotal, i.outTotal, i.createdDate, i.updatedDate, s.supplierId, s.supplierName, s.supplierAddress, s.numberPhone, s.email\n" +
//            "  FROM Invoice i, Supplier s\n" +
//            "  WHERE i.type = 1 AND i.activeFlag = 1 AND s.supplierId = i.supplierId;"
//            ,nativeQuery = true)
//    List<InvoiceSupplier> findInvoiceImport();

    @Query(value = "select * from Invoice inv where inv.type = 2", nativeQuery = true)
    List<Invoice> findInvoiceExport();
}
