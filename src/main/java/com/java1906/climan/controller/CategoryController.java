package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    //Get all category
    @GetMapping("/categories")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<Category>> showCategoryList(Model model, @RequestParam String categoryName) {
        List<Category> categoryList = categoryService.findAll(categoryName);
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        model.addAttribute("titlePage","Category List");
        model.addAttribute("categoryList",categoryList);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    //Get category by id
    @GetMapping("/categories/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getCategoryById(Model model,@PathVariable("id") Integer id) {
        System.out.println("Fetching category with id " + id);
        Category category = categoryService.findById(id).get();
        if (category == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("titlePage","Category List");
        model.addAttribute("category",category);
        return new ResponseEntity<Object>(category, HttpStatus.OK);
    }

    // Create category
    @PostMapping("/categories")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoryService.save(category);
        return new ResponseEntity<>("created!", HttpStatus.CREATED);
    }

    // Update category
    @PutMapping("/categories/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> updateCategory(@PathVariable("id") Integer id,
                                                 @RequestBody Category category) throws Exception {
        categoryService.update(id,category);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }

    // Delete category
    @DeleteMapping("/categories/{id}")
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
