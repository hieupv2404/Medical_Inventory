package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.InvoiceCustomer;
import com.java1906.climan.data.model.InvoiceCustomerReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceCustomerReportRepository extends JpaRepository<InvoiceCustomerReport, Integer> {
//    @Query( value="SELECT i.id, i.inTotal, i.outTotal, i.createdDate, i.updatedDate, s.supplierId, s.supplierName, s.supplierAddress, s.numberPhone, s.email\n" +
//            "  FROM Invoice i, Supplier s\n" +
//            "  WHERE i.type = 1 AND i.activeFlag = 1 AND s.supplierId = i.supplierId;"
//            ,nativeQuery = true)

        @Query(value="SELECT i.id, i.in_total, i.out_total, i.created_date, i.updated_date, s.id as 'customer_id', s.name, s.address, s.number_phone, s.email\n" +
                "                FROM Invoice i, Customer s\n" +
                "                WHERE i.type = 2 AND i.active_flag = 1 AND s.id = i.customer_id"
                ,nativeQuery = true)
        List<InvoiceCustomerReport> findInvoiceExport();
}
