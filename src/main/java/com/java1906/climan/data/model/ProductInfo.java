package com.java1906.climan.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "product_info")
public class ProductInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private String img_url;
    private Integer activeFlag;
    private Date createDate;
    private Date updateDate;

    @Transient
    private String categoryValueByFate;

    @Transient
    private String categoryValueByProperty;

    @Transient
    private double qtyInStock;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_info_category_value",
            joinColumns = {@JoinColumn(name = "productInfoId")},
            inverseJoinColumns = {@JoinColumn(name = "categoryValueId")})
    private List<CategoryValue> categoryValues;

    @OneToMany(fetch = FetchType.LAZY)
    private  List<InvoiceItem> invoiceItem ;

    @OneToMany(fetch = FetchType.LAZY)
    private  List<ProductInStock> productInStocks ;

    public ProductInfo() {
    }
public ProductInfo(String name,String description,String img_url,int activeFlag, Date createDate,Date updateDate,List<CategoryValue> categoryValues){
            this.name =name;
            this.description =description;
            this.img_url =img_url;
            this.activeFlag =activeFlag;
            this.createDate =createDate;
            this.updateDate =updateDate;
            this.categoryValues =categoryValues;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<CategoryValue> getCategoryValues() {
        return categoryValues;
    }

    public void setCategoryValues(List<CategoryValue> categoryValues) {
        this.categoryValues = categoryValues;
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

    public String getCategoryValueByFate() {
        return categoryValueByFate;
    }

    public ProductInfo setCategoryValueByFate(String categoryValueByFate) {
        this.categoryValueByFate = categoryValueByFate;
        return this;
    }

    public String getCategoryValueByProperty() {
        return categoryValueByProperty;
    }

    public ProductInfo setCategoryValueByProperty(String categoryValueByProperty) {
        this.categoryValueByProperty = categoryValueByProperty;
        return this;
    }

    public double getQtyInStock() {
        return qtyInStock;
    }

    public ProductInfo setQtyInStock(double qtyInStock) {
        this.qtyInStock = qtyInStock;
        return this;
    }
}
