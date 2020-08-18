package com.java1906.climan.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class InvoiceItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="product_info_id")
    @JsonIgnore
    private ProductInfo productInfo ;

    @ManyToOne
    @JoinColumn(name ="invoice_id")
    @JsonIgnore
    private Invoice invoice;

    private int qty;    // so luong

    @ManyToOne
    @JoinColumn(name ="unit_id")
    @JsonIgnore
    private Unit unit ;

    private double priceIn; // don gia nhao
    private double priceOut; // don gia xuat
    private double priceInTotal;    // tong gia nhap = priceIn * qty
    private double priceOutTotal;   // tong gia xuat

    public InvoiceItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(double priceIn) {
        this.priceIn = priceIn;
    }

    public double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(double priceOut) {
        this.priceOut = priceOut;
    }

    public double getPriceInTotal() {
        return priceInTotal;
    }

    public void setPriceInTotal(double priceInTotal) {
        this.priceInTotal = priceInTotal;
    }

    public double getPriceOutTotal() {
        return priceOutTotal;
    }

    public void setPriceOutTotal(double priceOutTotal) {
        this.priceOutTotal = priceOutTotal;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
