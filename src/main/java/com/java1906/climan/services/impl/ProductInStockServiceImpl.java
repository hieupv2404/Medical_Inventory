package com.java1906.climan.services.impl;

import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.data.model.ProductInStock;
import com.java1906.climan.data.model.ProductInfo;
import com.java1906.climan.data.repo.InvoiceItemRepository;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.ProductInStockRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.services.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductInStockServiceImpl implements ProductInStockService {
    @Autowired
    private ProductInStockRepository productInStockRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInStock> getAll() {
        return productInStockRepository.findAll();
    }

    @Override
    public void saveOrUpdate(InvoiceItem invoiceItem) {
        if(invoiceItem.getProductInfo()!=null)
        {
            int productId = invoiceItem.getProductInfo().getId();
            List<ProductInStock> productInStocks = productInStockRepository.findAll();
            for(ProductInStock productInStock : productInStocks) {
                if (productInStock.getProductInfo().getId() == productId) {
                    if (invoiceItem.getInvoice().getType() == 1) {
                        productInStock.setQty(productInStock.getQty() + invoiceItem.getQty());
                        productInStock.setPrice(invoiceItem.getPriceInTotal());
                    } else if (invoiceItem.getInvoice().getType() == 2) {
                        productInStock.setQty(productInStock.getQty() - invoiceItem.getQty());
                        productInStock.setPrice(invoiceItem.getPriceOutTotal());
                    }

                }
            }
            if (invoiceItem.getInvoice().getType()==1)
            {
                ProductInStock productInStock = new ProductInStock();
                productInStock.setProductInfo(invoiceItem.getProductInfo());
                productInStock.setQty(invoiceItem.getQty());
                productInStock.setUnit(invoiceItem.getUnit());
                productInStock.setPrice(invoiceItem.getPriceInTotal());
                productInStock.setActiveFlag(1);
                productInStock.setCreatedDate(new Date());
                productInStock.setUpdatedDate(new Date());
                productInStockRepository.saveAndFlush(productInStock);
            }


        }
    }

}
