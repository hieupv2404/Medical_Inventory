package com.java1906.climan.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    private String name;
    private String address;
    private String numberPhone;
    private String email;




    public Supplier() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer supplierId) {
        this.id = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String supplierName) {
        this.name = supplierName;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String supplierAddress) {
        this.address = supplierAddress;
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
