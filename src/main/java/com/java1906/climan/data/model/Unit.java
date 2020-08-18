package com.java1906.climan.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private List<InvoiceItem> invoiceItem;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductInStock> productInStocks;

    public Unit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InvoiceItem> getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public List<ProductInStock> getProductInStocks() {
        return productInStocks;
    }

    public void setProductInStocks(List<ProductInStock> productInStocks) {
        this.productInStocks = productInStocks;
    }
}
