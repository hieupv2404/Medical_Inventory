package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.InvoiceSupplier;
import com.java1906.climan.data.model.InvoiceSupplierReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceSupplierReportRepository extends JpaRepository<InvoiceSupplierReport, Integer> {
//    @Query( value="SELECT i.id, i.inTotal, i.outTotal, i.createdDate, i.updatedDate, s.supplierId, s.supplierName, s.supplierAddress, s.numberPhone, s.email\n" +
//            "  FROM Invoice i, Supplier s\n" +
//            "  WHERE i.type = 1 AND i.activeFlag = 1 AND s.supplierId = i.supplierId;"
//            ,nativeQuery = true)

        @Query(value="SELECT i.id, i.code, i.in_total, i.out_total, i.created_date, i.updated_date, s.supplier_id, s.supplier_name, s.supplier_address, s.number_phone, s.email\n" +
                "  FROM invoice i, supplier s\n" +
                "  WHERE i.type = 1 AND i.active_flag = 1 AND s.supplier_id = i.supplier_id;"
                ,nativeQuery = true)
        List<InvoiceSupplierReport> findInvoiceImport();
}
