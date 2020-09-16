package com.java1906.climan.data.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoCategoryValueId implements Serializable {
    private Integer productInfoId;
    private Integer categoryValueId;
}
