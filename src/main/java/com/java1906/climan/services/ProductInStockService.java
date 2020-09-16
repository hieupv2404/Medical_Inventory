package com.java1906.climan.services;


import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.data.model.ProductInStock;
import com.java1906.climan.data.model.UnitConstant;

import java.util.List;

public interface ProductInStockService {
    List<ProductInStock> findAll();

    void saveOrUpdate(InvoiceItem invoiceItem);

    double convertUnit(double qtyInp, UnitConstant unitInp, double qtySaved, UnitConstant unitSaved);
}
