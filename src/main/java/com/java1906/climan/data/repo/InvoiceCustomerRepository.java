package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.InvoiceCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceCustomerRepository extends JpaRepository<InvoiceCustomer, Integer> {
//    @Query( value="SELECT i.id, i.inTotal, i.outTotal, i.createdDate, i.updatedDate, s.supplierId, s.supplierName, s.supplierAddress, s.numberPhone, s.email\n" +
//            "  FROM Invoice i, Supplier s\n" +
//            "  WHERE i.type = 1 AND i.activeFlag = 1 AND s.supplierId = i.supplierId;"
//            ,nativeQuery = true)

        @Query(value="SELECT i.id, i.code, i.in_total, i.out_total, i.created_date, i.updated_date, c.id as 'customer_id', c.name, c.address, c.number_phone, c.email\n" +
                "                FROM invoice i, customer c\n" +
                "                WHERE i.type = 2 AND i.active_flag = 1 AND c.id = i.customer_id AND c.name like %:name% AND i.code like %:code% AND c.email like %:email%"
                ,nativeQuery = true)
        List<InvoiceCustomer> findInvoiceExport(@Param("name") String name, @Param("code") String code, @Param("email") String email);

        @Query(value="select max(id)+1 from invoice",nativeQuery=true )
        int findMaxIdInInvoice();

        @Query(value="SELECT i.id, i.code, i.in_total, i.out_total, i.created_date, i.updated_date, c.id as 'customer_id', c.name, c.address, c.number_phone, c.email\n" +
                "                FROM invoice i, customer c\n" +
                "                WHERE i.type = 2 AND i.active_flag = 1 AND c.id = i.customer_id "
                ,nativeQuery = true)
        List<InvoiceCustomer> findAllInvoiceExport();

        @Query(value="SELECT i.id, i.code, i.in_total, i.out_total, i.created_date, i.updated_date, c.id as 'customer_id', c.name, c.address, c.number_phone, c.email\n" +
                "                FROM invoice i, customer c\n" +
                "                WHERE i.type = 2 AND i.active_flag = 1 AND c.id = i.customer_id AND i.id=?1"
                ,nativeQuery = true)
        InvoiceCustomer findExportById(Integer id);

}
