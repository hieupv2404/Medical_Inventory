package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.Paging;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryService;
import com.java1906.climan.services.ICategoryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/categoryValue")
@RestController
public class CategoryValueController {
    @Autowired
    private ICategoryValueService categoryValueService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value= {"/list","/list/"})
    public String redirect() {
        return "redirect:/list/1";
    }

    //Get all categoryValue
    @GetMapping("/list/{page}")
    @HasRole({"STAFF", "ADMIN","DOCTOR"})
    public String showCategoryValueList(Model model, @ModelAttribute("categoryValueListForm") CategoryValue categoryValue, @PathVariable("page") int page ){
        Paging paging = new Paging(5);
        paging.setIndexPage(page);
        List<CategoryValue> categoryValues = categoryValueService.findAll();

        model.addAttribute("paging",paging);
        model.addAttribute("categoryValues",categoryValues);
        return "categoryValue-list";
    }
//GET ById
    @GetMapping("/{id}")
    @HasRole({"STAFF", "ADMIN","DOCTOR"})
    public String getCategoryValueById(Model model, @ModelAttribute("categoryValueListForm") CategoryValue categoryValue, @PathVariable("page") int page,@PathVariable("id") int id) {
        Paging paging = new Paging(5);
        paging.setIndexPage(page);
        categoryValue = categoryValueService.findById(id).get();
        model.addAttribute("paging",paging);
        model.addAttribute("categoryValues",categoryValue);
        return "categoryValue-list";

    }

    //add category value
    @GetMapping("/category/{categoryId}/add")
    @HasRole({"STAFF", "ADMIN"})
    public String addCategoryValue(Model model, @PathVariable("categoryId") int categoryId)
    {
        model.addAttribute("tiltePage","Add Category Value");
        model.addAttribute("modelForm",new CategoryValue());
        model.addAttribute("viewOnly",false);

         Category category = categoryService.findById(categoryId).get();
        Map<String, String> mapCategory = new HashMap<>();
        mapCategory.put(String.valueOf(category.getId()), category.getName());

        model.addAttribute("mapCategory",mapCategory);
        return "categoryValue-action";
    }

    // edit category value
    @GetMapping("/edit/{id}")
    public String editCategoryValue(Model model , @PathVariable("id") int id) {

        CategoryValue categoryValue = categoryValueService.findById(id).get();
        if(categoryValue!=null) {

            List<Category> categories = categoryService.findAll();
            Map<String, String> mapCategory = new HashMap<>();
            for(Category category : categories) {
                mapCategory.put(String.valueOf(category.getId()), category.getName());
            }
            categoryValue.setCategoryId(categoryValue.getCategory().getId());

            model.addAttribute("mapCategory",mapCategory);
            model.addAttribute("titlePage", "Edit Category Value");
            model.addAttribute("modelForm", categoryValue);
            model.addAttribute("viewOnly", false);
            return "categoryValue-action";
        }
        return "redirect:/list";
    }

    // view category value
    @GetMapping("/view/{id}")
    public String view(Model model , @PathVariable("id") int id) {

        CategoryValue categoryValue = categoryValueService.findById(id).get();
        if(categoryValue!=null) {
            model.addAttribute("titlePage", "View Category Value");
            model.addAttribute("modelForm", categoryValue);
            model.addAttribute("viewOnly", true);
            return "categoryValue-action";
        }
        return "redirect:/list";
    }

    // Create categoryValue value
    @PostMapping("/categoryValue/{categoryId}/save")
    @HasRole({"STAFF", "ADMIN"})
    public String saveCategoryValue(Model model, @ModelAttribute("modelForm") CategoryValue categoryValue, BindingResult result, HttpSession session, @PathVariable("categoryId") int categoryId) {
        if(result.hasErrors()) {
            if(categoryValue.getId()!=null) {
                model.addAttribute("titlePage", "Edit Category Value");
            }else {
                model.addAttribute("titlePage", "Add Category Value");
            }
            Category category = categoryService.findById(categoryId).get();
            Map<String, String> mapCategory = new HashMap<>();
            mapCategory.put(String.valueOf(category.getId()), category.getName());

            model.addAttribute("mapCategory", mapCategory);
            model.addAttribute("modelForm", categoryValue);
            model.addAttribute("viewOnly", false);
            return "categoryValue-action";

        }

        Category category = categoryService.findById(categoryId).get();
        categoryValue.setCategory(category);

        categoryValueService.save(categoryValue);

        return "redirect:/list";
    }

    // Update category value
//    @PutMapping("/{categoryValueId}")
//    @HasRole({"STAFF", "ADMIN"})
//    public ResponseEntity<CategoryValue> updateCategoryDetail(@PathVariable("categoryValueId") Integer categoryValueId,
//                                                              @RequestBody CategoryValue categoryValue) {
//        return new ResponseEntity<CategoryValue>(categoryValueService.update(categoryValueId,categoryValue), HttpStatus.OK);
//    }

    // Delete category value
    @DeleteMapping("/{categoryValueId}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<String> deleteCategoryDetail(@PathVariable("categoryValueId") Integer categoryValueId) {
        categoryValueService.delete(categoryValueId);
        return new ResponseEntity<String>("Delete Ok",HttpStatus.OK);
    }
}
