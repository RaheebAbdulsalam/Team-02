package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Category;
import com.gamestation.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    // Returns admin category page and category list
    @GetMapping
    public ModelAndView getAdminCategoryPage() {
        ModelAndView mav = new ModelAndView("admin/category");
        mav.addObject("categories", categoryService.getAllCategories());
        return mav;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if(categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id) {
        Category category = categoryService.getCategoryById(id);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Returns page for creating categories
    @GetMapping("/create")
    public ModelAndView getAddCategoryPage() {
        ModelAndView mav = new ModelAndView("admin/addCategory");
        mav.addObject("category", new Category()); // create a new empty category object to bind with the form
        return mav;
    }

    @PostMapping
    public RedirectView createCategory(@ModelAttribute("category") Category category) {
        categoryService.createCategory(category);
        return new RedirectView("/admin/category");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getUpdateCategoryPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/editCategory");
        Category category = categoryService.getCategoryById(id);
        mav.addObject("category", category);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public RedirectView updateCategory(@PathVariable("id") Integer id, @ModelAttribute("category") Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return new RedirectView("/admin/category");
    }

    // method for deleting category, and reloading page
    @DeleteMapping("/{id}")
    public RedirectView deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return new RedirectView("/admin/category");
    }
}