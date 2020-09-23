package com.java1906.climan.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class InvoiceCustomerReport implements Serializable {
    @Id
    private Integer id;
    private double inTotal;       // tong gia nhap
    private double outTotal;    // tong gia xuat
    private Date createdDate;
    private Date updatedDate;
    private Integer customerId;
    private String name;
    private String address;
    private String numberPhone;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public double getInTotal() {
        return inTotal;
    }

    public void setInTotal(double inTotal) {
        this.inTotal = inTotal;
    }

    public double getOutTotal() {
        return outTotal;
    }

    public void setOutTotal(double outTotal) {
        this.outTotal = outTotal;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
