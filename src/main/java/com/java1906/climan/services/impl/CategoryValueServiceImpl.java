package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.repo.CategoryRepository;
import com.java1906.climan.data.repo.CategoryValueRepository;
import com.java1906.climan.services.ICategoryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryValueServiceImpl implements ICategoryValueService {
    @Autowired
    private CategoryValueRepository categoryValueRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ICategoryValueService categoryValueService;
    @Override
    public List<CategoryValue> findAll() {
        return categoryValueRepository.findAll();
    }

    @Override
    public Optional<CategoryValue> findById(Integer categoryValueId) {
        if(!categoryValueRepository.existsById(categoryValueId)){
            try{
                throw new ResourceNotFoundException("CategoryValue with"+categoryValueId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        return categoryValueRepository.findById(categoryValueId);
    }

    @Override
    public void save(CategoryValue categoryValue) {
        if (categoryValue.getId() != null || categoryValue.getId() != 0)
        {
            try {

                categoryValueService.update(categoryValue.getId(),categoryValue);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        else {
            try {
                List<CategoryValue> categoryValues = new ArrayList<>();

                //save lai
                categoryValue.setActiveFlag(1);
                categoryValue.setCreateDate(new Date());
                categoryValue.setUpdateDate(new Date());
                categoryValue = categoryValueRepository.save(categoryValue);
                //categoryValue chua category
                categoryValues.add(categoryValue);
                categoryValue.getCategory().setCategoryValue(categoryValues);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(int categoryValueId, CategoryValue categoryValueRequest) {
        if(!categoryValueRepository.existsById(categoryValueId)){
            try {
                throw new ResourceNotFoundException("CategoryValue with id "+categoryValueId + "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        CategoryValue categoryValue =categoryValueRepository.findById(categoryValueId).get();
        if(categoryValue.getId() == null || categoryValue.getId() ==0){
            try {
                throw new ResourceNotFoundException("CategoryValue with"+categoryValueId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        CategoryValue categoryValue1 =categoryValue;
        categoryValue1.setName(categoryValueRequest.getName());
        categoryValue1.setDescription(categoryValueRequest.getDescription());
        categoryValue1.setActiveFlag(categoryValueRequest.getActiveFlag());
        categoryValue1.setCategory(categoryValueRequest.getCategory());
        categoryValue1.setUpdateDate(new Date());
        categoryValue1.setCreateDate(categoryValue1.getCreateDate());

        categoryValue= categoryValueRepository.save(categoryValue1);
    }

    @Override
    public void delete(Integer categoryValueId) {
        if (!categoryValueRepository.existsById(categoryValueId)) {
            try {
                throw new ResourceNotFoundException("CategoryValue with id " + categoryValueId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        categoryValueRepository.deleteById(categoryValueId);
    }


    @Override
    public Optional<CategoryValue> findByCategory(Integer categoryId) {
        return Optional.empty();
    }

    @Override
    public List<CategoryValue> findAllByNameContatining(String name) {
        return null;
    }
}
