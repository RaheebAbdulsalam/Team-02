package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    // Returns admin product page and product list
    @GetMapping
    public ModelAndView getAdminProductPage() {
        ModelAndView mav = new ModelAndView("admin/product");
        return mav;
    }
    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // returns page to add products
    @GetMapping("/create")
    public ModelAndView getAddProductPage() {
        ModelAndView mav = new ModelAndView("admin/addProduct");
        mav.addObject("product", new Product());
        return mav;
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // returns
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
    }
}