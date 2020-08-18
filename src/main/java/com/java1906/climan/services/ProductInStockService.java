package com.java1906.climan.services;


import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.data.model.ProductInStock;

import java.util.List;

public interface ProductInStockService {
    List<ProductInStock> getAll();

    void saveOrUpdate(InvoiceItem invoiceItem);
}
