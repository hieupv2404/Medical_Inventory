package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.repo.CategoryRepository;
import com.java1906.climan.data.repo.CategoryValueRepository;
import com.java1906.climan.services.ICategoryValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryValueServiceImpl implements ICategoryValueService {
    @Autowired
    private CategoryValueRepository categoryValueRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryValue> findAll(String categoryValueName) {
        if (categoryValueName != null && !StringUtils.isEmpty(categoryValueName))
        {
            return categoryValueRepository.findCategoryValueByName(categoryValueName);
        }
        else {
            return categoryValueRepository.findAll();
        }

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
    public CategoryValue save(int categoryId,CategoryValue categoryValue) {
        List<CategoryValue> categoryValues = new ArrayList<>();
        Category category1 = new Category();
        Optional<Category> categoryById = categoryRepository.findById(categoryId);
        if(!categoryById.isPresent()){
            try {
                throw new ResourceNotFoundException("CategoryValue with id "+categoryId+ "does not exist");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        //goi thang id category ra
        Category category = categoryById.get();
        //set vao category
        categoryValue.setCategory(category);
        //save lai
        CategoryValue categoryValue1 =categoryValueRepository.save(categoryValue);
        //categoryValue chua category
        categoryValues.add(categoryValue1);
        category1.setCategoryValue(categoryValues);
        return categoryValue1;
    }

    @Override
    public CategoryValue update(int categoryValueId, CategoryValue categoryValueRequest) {
        if(!categoryValueRepository.existsById(categoryValueId)){
            try {
                throw new ResourceNotFoundException("CategoryValue with id "+categoryValueId + "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Optional<CategoryValue> categoryValue =categoryValueRepository.findById(categoryValueId);
        if(!categoryValue.isPresent()){
            try {
                throw new ResourceNotFoundException("CategoryValue with"+categoryValueId + " not fount");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        CategoryValue categoryValue1 =categoryValue.get();
        categoryValue1.setName(categoryValueRequest.getName());
        categoryValue1.setDescription(categoryValueRequest.getDescription());
        categoryValue1.setActiveFlag(categoryValueRequest.getActiveFlag());
        categoryValue1.setCategory(categoryValueRequest.getCategory());
        categoryValue1.setUpdateDate(categoryValueRequest.getUpdateDate());
        categoryValue1.setCreateDate(categoryValue1.getCreateDate());

        return categoryValueRepository.save(categoryValue1);
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

    @Override
    public void save(CategoryValue categoryValue) {
        categoryValueRepository.save(categoryValue);
    }
}
