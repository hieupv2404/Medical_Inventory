package com.java1906.climan.services.impl;


import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.ProductInfo;
import com.java1906.climan.data.repo.CategoryValueRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.services.IProducInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ProductServiceImpl implements IProducInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public Iterable<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public Optional<ProductInfo> findById(int id) {
        return productInfoRepository.findById(id);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

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
