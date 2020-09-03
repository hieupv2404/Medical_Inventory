package com.java1906.climan.dto;

import java.util.List;

public class CustomerDTO {
    private int customerId;
    private String name;
    private String address;
    private String numberPhone;
    private String email;
    private List<Integer> invoiceId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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

    public List<Integer> getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(List<Integer> invoiceId) {
        this.invoiceId = invoiceId;
    }
}
