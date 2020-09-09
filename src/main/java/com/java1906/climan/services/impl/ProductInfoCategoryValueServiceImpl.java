package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.ProductInfoCategoryValue;
import com.java1906.climan.data.repo.ProductInfoCategoryValueRepository;
import com.java1906.climan.services.IProductInfoCategoryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductInfoCategoryValueServiceImpl implements IProductInfoCategoryValueService {

    @Autowired
    private ProductInfoCategoryValueRepository productInfoCategoryValueRepository;



    @Override
    public List<ProductInfoCategoryValue> findAll() {
        return (List<ProductInfoCategoryValue>) productInfoCategoryValueRepository.findAll();
    }

    @Override
    public Optional<ProductInfoCategoryValue> findById(Integer id) {
        if (!productInfoCategoryValueRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("ProductInfoCategoryValue with"+ id+ "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return productInfoCategoryValueRepository.findById(id);
    }

    @Override
    public ProductInfoCategoryValue save(ProductInfoCategoryValue productInfoCategoryValue) {
        return productInfoCategoryValueRepository.save(productInfoCategoryValue);
    }
    

//    @Override
//    public ProductInfoCategoryValue update(int productInfoCategoryValueId, ProductInfoCategoryValue productInfoCategoryValue){
//        if (!productInfoCategoryValueRepository.existsById(productInfoCategoryValueId)) {
//            try {
//                throw new ResourceNotFoundException("Author with id " + productInfoCategoryValueId + " not found");
//            } catch (ResourceNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        Optional<ProductInfoCategoryValue> productInfoCategoryValueOne =productInfoCategoryValueRepository.findById(productInfoCategoryValueId);
//
//        if (!productInfoCategoryValueOne.isPresent()) {
//            try {
//                throw new ResourceNotFoundException("Author with id " + productInfoCategoryValueId + " not found");
//            } catch (ResourceNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        ProductInfoCategoryValue productInfoCategoryValue1 = productInfoCategoryValueOne.get();
//        productInfoCategoryValue1.setName(productInfoCategoryValue.getName());
//        productInfoCategoryValue1.setDescription(productInfoCategoryValue.getDescription());
//        productInfoCategoryValue1.setCreateDate(productInfoCategoryValue1.getCreateDate());
//        productInfoCategoryValue1.setUpdateDate(new Date());
//        return productInfoCategoryValueRepository.save(productInfoCategoryValue1);
//    }

    @Override
    public void delete(Integer productInfoCategoryValueId) {
        if (!productInfoCategoryValueRepository.existsById(productInfoCategoryValueId)) {
            try {
                throw new ResourceNotFoundException("Author with id " + productInfoCategoryValueId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        productInfoCategoryValueRepository.deleteById(productInfoCategoryValueId);
    }

    @Override
    public ProductInfoCategoryValue findByProductIdAndCategoryValueId(Integer productInfoId, Integer categoryValueId) {
        return productInfoCategoryValueRepository.findByProductIdAndCategoryValueId(productInfoId,categoryValueId);
    }
}
