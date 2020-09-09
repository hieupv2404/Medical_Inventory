package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryService;
import com.java1906.climan.services.ICategoryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryValueController {
    @Autowired
    private ICategoryValueService categoryValueService;

    @Autowired
    private ICategoryService   categoryService;

    //Get all category
    @GetMapping("/categoryValue")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<List<CategoryValue>> showCategoryList(Model model) {
        List<CategoryValue> categoryValueList = (List<CategoryValue>) categoryValueService.findAll();
        if (categoryValueList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        model.addAttribute("titlePage","Category Value List");
        model.addAttribute("categoryValueList",categoryValueList);
        return new ResponseEntity<>(categoryValueList, HttpStatus.OK);
    }

    //Get category by id
    @GetMapping("/categoryValue/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getCategoryDetailById(Model model,@PathVariable("id") Integer id) {
        System.out.println("Fetching category detail with id " + id);
        CategoryValue categoryValue = categoryValueService.findById(id).get();
        if (categoryValue == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("titlePage","Category Value List");
        model.addAttribute("categoryValue",categoryValue);
        return new ResponseEntity<Object>(categoryValue, HttpStatus.OK);
    }

    // Create category
    @PostMapping("/categoryValue")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> createCategoryDetail(@RequestBody CategoryValue categoryValue) {
        if (categoryValue == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // lấy đc id của thg cha: category
        // set id vừa lấy, vào category_id của categoryValue.

        categoryValueService.save(categoryValue);
        return new ResponseEntity<>("created!", HttpStatus.CREATED);
    }

    // Update category detail
    @PutMapping("/categoryValue/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> updateCategoryDetail(@PathVariable("id") Integer id,
                                                       @RequestBody CategoryValue categoryValue) {
        categoryValueService.update(id,categoryValue);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }

    // Delete category detail
    @DeleteMapping("/categoryValue/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<CategoryValue> deleteCategoryDetail(@PathVariable("id") Integer id) {
        CategoryValue categoryValue = categoryValueService.findById(id).get();
        if (categoryValue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryValueService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
