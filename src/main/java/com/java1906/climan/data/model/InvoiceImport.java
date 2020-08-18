package com.java1906.climan.data.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class InvoiceImport implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "invoice_id")

    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public InvoiceImport() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
