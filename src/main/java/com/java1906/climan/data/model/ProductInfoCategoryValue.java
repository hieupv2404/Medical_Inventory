package com.java1906.climan.data.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name= "product_info_category_value")
@IdClass(ProductInfoCategoryValueId.class)
public class ProductInfoCategoryValue {

//   @EmbeddedId ProductInfoCategoryValueId productInfoCategoryValueId;

    @Id
    private Integer productInfoId;

    @Id
    private Integer categoryValueId;

    private String test;

    public ProductInfoCategoryValue() {
    }


}
