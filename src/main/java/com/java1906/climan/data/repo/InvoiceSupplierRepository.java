package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.InvoiceSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceSupplierRepository extends JpaRepository<InvoiceSupplier, Integer> {
//    @Query( value="SELECT i.id, i.inTotal, i.outTotal, i.createdDate, i.updatedDate, s.supplierId, s.supplierName, s.supplierAddress, s.numberPhone, s.email\n" +
//            "  FROM Invoice i, Supplier s\n" +
//            "  WHERE i.type = 1 AND i.activeFlag = 1 AND s.supplierId = i.supplierId;"
//            ,nativeQuery = true)

        @Query(value="SELECT i.id, i.code, i.in_total, i.out_total, i.created_date, i.updated_date, s.supplier_id, s.supplier_name, s.supplier_address, s.number_phone, s.email\n" +
                "  FROM invoice i, supplier s" +
                "  WHERE i.type = 1 AND i.active_flag = 1 AND s.supplier_id = i.supplier_id AND c.name like %:name% AND s.code like %:code% AND c.email like %:email%"
                ,nativeQuery = true)
        List<InvoiceSupplier> findInvoiceImport(@Param("name") String name, @Param("code") String code, @Param("email") String email);

        @Query(value="select max(id)+1 from invoice",nativeQuery=true )
        int findMaxIdInInvoice();

        @Query(value="SELECT i.id, i.code, i.in_total, i.out_total, i.created_date, i.updated_date, s.supplier_id, s.supplier_name, s.supplier_address, s.number_phone, s.email\n" +
                "  FROM invoice i, supplier s" +
                "  WHERE i.type = 1 AND i.active_flag = 1 AND s.supplier_id = i.supplier_id "
                ,nativeQuery = true)
        List<InvoiceSupplier> findAllInvoiceImport();
}
