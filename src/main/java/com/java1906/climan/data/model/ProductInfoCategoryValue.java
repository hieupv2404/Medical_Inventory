package com.java1906.climan.data.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "product_info_category_value")
public class ProductInfoCategoryValue implements Serializable {
    @Id

    private Integer productInfoId;

    @Id
    private Integer categoryValueId;

    public ProductInfoCategoryValue() {
    }

    public Integer getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Integer productInfoId) {
        this.productInfoId = productInfoId;
    }

    public Integer getCategoryValueId() {
        return categoryValueId;
    }

    public void setCategoryValueId(Integer categoryValueId) {
        this.categoryValueId = categoryValueId;
    }
}
