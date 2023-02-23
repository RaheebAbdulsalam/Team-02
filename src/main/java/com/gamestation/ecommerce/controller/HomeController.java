package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Category;
import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    // Returns home page
    @GetMapping
    public ModelAndView getAdminHomePage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    // 1-5 for now
    @GetMapping("/categoryproducts/{id}")
    public ModelAndView getCategoryPage(@PathVariable("id") Integer id) {
        Category category = categoryService.getCategoryById(id);
        ModelAndView mav = new ModelAndView("productPage");
        mav.addObject("category", category);
        mav.addObject("products", category.getProducts());
        return mav;
    }


}
