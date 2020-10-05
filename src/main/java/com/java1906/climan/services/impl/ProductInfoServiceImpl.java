package com.java1906.climan.services.impl;


import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.ProductInfo;
import com.java1906.climan.data.repo.CategoryValueRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.services.IProducInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductInfoServiceImpl implements IProducInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findAll(String productName, String categoryValue) {
//        if(productName != null && !StringUtils.isEmpty(productName) || categoryValue != null && !StringUtils.isEmpty(categoryValue))
//        {
//            return invoiceCustomerRepository.findInvoiceExport(productName, categoryValue);
//        }
//        else {
//            return invoiceCustomerRepository.findAllInvoiceExport();
//        }
        return productInfoRepository.findAll();
    }

    @Override
    public Optional<ProductInfo> findById(int id) {
        return productInfoRepository.findById(id);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

        productInfo.setActiveFlag(1);
        productInfo.setCreateDate(new Date());
        productInfo.setUpdateDate(new Date());
       return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo update(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void  delete(int id) {
        productInfoRepository.deleteById(id);
    }


}
