package com.java1906.climan.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer supplierId;
    private String supplierName;
    private String supplierAddress;
    private String numberPhone;
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private  List<InvoiceImport> invoiceImports;


    public Supplier() {
    }

    public List<InvoiceImport> getInvoiceImports() {
        return invoiceImports;
    }

    public void setInvoiceImports(List<InvoiceImport> invoiceImports) {
        this.invoiceImports = invoiceImports;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
