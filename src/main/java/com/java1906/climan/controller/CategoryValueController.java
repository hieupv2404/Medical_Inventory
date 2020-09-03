package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryService;
import com.java1906.climan.services.ICategoryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryValueController {
    @Autowired
    private ICategoryValueService categoryDetailService;

    @Autowired
    private ICategoryService   categoryService;

    //Get all category
    @GetMapping("/categoryDetail")
    @CrossOrigin(origins = "http://localhost:4200")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<CategoryValue>> showCategoryList() {
        List<CategoryValue> categoryDetailList = (List<CategoryValue>) categoryDetailService.findAll();
        if (categoryDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryDetailList, HttpStatus.OK);
    }

    //Get category by id
    @GetMapping("/categoryDetail/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getCategoryDetailById(@PathVariable("id") Integer id) {
        System.out.println("Fetching category detail with id " + id);
        CategoryValue categoryDetail = categoryDetailService.findById(id).get();
        if (categoryDetail == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(categoryDetail, HttpStatus.OK);
    }

    // Create category
    @PostMapping("/categoryDetail")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> createCategoryDetail(@RequestBody CategoryValue categoryDetail) {
        if (categoryDetail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // lấy đc id của thg cha: category
        // set id vừa lấy, vào category_id của categoryDetail.

        categoryDetailService.save(categoryDetail);
        return new ResponseEntity<>("created!", HttpStatus.CREATED);
    }

    // Update category detail
    @PutMapping("/categoryDetail/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> updateCategoryDetail(@PathVariable("id") Integer id,
                                                       @RequestBody CategoryValue categoryDetail) {
        System.out.println("Updating Category Detail " + id);
        CategoryValue currentCategoryDetail = categoryDetailService.findById(id).get();
        if (currentCategoryDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryDetailService.save(categoryDetail);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }

    // Delete category detail
    @DeleteMapping("/categoryDetail/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<CategoryValue> deleteCategoryDetail(@PathVariable("id") Integer id) {
        CategoryValue categoryDetail = categoryDetailService.findById(id).get();
        if (categoryDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
