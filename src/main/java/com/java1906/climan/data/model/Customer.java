package com.java1906.climan.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;
    private String name;
    private String address;
    private String numberPhone;
    private String email;




    public Customer() {
    }

    public int getId() {
        return id;
    }


    public void setId(int customerId) {
        this.id = customerId;
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
