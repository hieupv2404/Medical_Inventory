package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    //Get all category
    @GetMapping("/category")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<Category>> showCategoryList() {
        List<Category> categoryList = (List<Category>) categoryService.getAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    //Get category by id
    @GetMapping("/category/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") Integer id) {
        System.out.println("Fetching category with id " + id);
        Category category = categoryService.findById(id).get();
        if (category == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(category, HttpStatus.OK);
    }

    // Create category
    @PostMapping("/category")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoryService.save(category);
        return new ResponseEntity<>("created!", HttpStatus.CREATED);
    }

    // Update category
    @PutMapping("/category/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> updateCategory(@PathVariable("id") Integer id,
                                                 @RequestBody Category category) {
        System.out.println("Updating Category " + id);
        Category currentCategory = categoryService.findById(id).get();
        if (currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.save(category);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }

    // Delete category
    @DeleteMapping("/category/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id).get();
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
