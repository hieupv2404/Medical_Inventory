package com.java1906.climan.controller;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.Paging;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;


    @RequestMapping(value= {"/category/list","/category/list/"})

    public String redirect() {
        return "redirect:/category/list/1";
    }
    //Get all category
    @GetMapping("/category/list/{page}")
    @HasRole({"STAFF", "ADMIN","DOCTOR"})
    public String showCategoryList(Model model, @ModelAttribute("categoryListForm") Category category, @PathVariable("page") int page ){
        Paging paging = new Paging(5);
        paging.setIndexPage(page);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("paging",paging);
        model.addAttribute("categories",categories);
        return "category-list";
    }

    //Get category by id
    @GetMapping("/category/{categoryId}/{page}")
    @HasRole({"STAFF", "ADMIN","DOCTOR"})
    public String getCategoryById(Model model, @ModelAttribute("categoryListForm") Category category, @PathVariable("page") int page,@PathVariable("categoryId") int categoryId) {
        Paging paging = new Paging(5);
        paging.setIndexPage(page);
        category = categoryService.findById(categoryId).get();
        model.addAttribute("paging",paging);
        model.addAttribute("categories",category);
        return "category-list";

    }

    @GetMapping("/category/add")
    @HasRole({"STAFF", "ADMIN"})
    public String addCategory(Model model)
    {
        model.addAttribute("tiltePage","Add Category");
        model.addAttribute("modelForm",new Category());
        model.addAttribute("viewOnly",false);
        return "category-action";
    }

    @GetMapping("/category/edit/{id}")
    public String edit(Model model , @PathVariable("id") int id) {

        Category category = categoryService.findById(id).get();
        if(category!=null) {
            model.addAttribute("titlePage", "Edit Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", false);
            return "category-action";
        }
        return "redirect:/category/list";
    }

    @GetMapping("/category/view/{id}")
    public String view(Model model , @PathVariable("id") int id) {

        Category category = categoryService.findById(id).get();
        if(category!=null) {
            model.addAttribute("titlePage", "View Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", true);
            return "category-action";
        }
        return "redirect:/category/list";
    }

    // Create category
    @PostMapping("/category/save")
    @HasRole({"STAFF", "ADMIN"})
    public String saveCategory(Model model, @ModelAttribute("modelForm") Category category, BindingResult result, HttpSession session) {
        if(result.hasErrors()) {
            if(category.getId()!=null) {
                model.addAttribute("titlePage", "Edit Category");
            }else {
                model.addAttribute("titlePage", "Add Category");
            }

            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", false);
            return "category-action";

        }
        categoryService.save(category);
        return "redirect:/category/list";
    }
    // Update category
//    @PutMapping("/category/{categoryId}")
//    @HasRole({"STAFF", "ADMIN"})
//    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Integer categoryId,
//                                                 @RequestBody Category category) throws Exception {
//            return new ResponseEntity<Category>(categoryService.update(categoryId,category),HttpStatus.NOT_FOUND);
//    }

    // Delete category
    @DeleteMapping("/category/{categoryId}")
    @HasRole({"STAFF", "ADMIN"})
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.delete(categoryId);
        return "redirect: /category/list";
    }

}
