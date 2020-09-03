package com.java1906.climan.dto;

import com.java1906.climan.data.model.Invoice;

import java.util.List;

public class SupplierDTO {
    private Integer supplierId;
    private String supplierName;
    private String supplierAddress;
    private String numberPhone;
    private String email;
    private List<Integer> invoiceId;

    public Integer getSupplierId() {
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

    public List<Integer> getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(List<Integer> invoiceId) {
        this.invoiceId = invoiceId;
    }
}
