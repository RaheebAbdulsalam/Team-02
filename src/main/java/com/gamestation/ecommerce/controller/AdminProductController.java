package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Category;
import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.service.CategoryService;
import com.gamestation.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    // Returns admin product page and product list
    @GetMapping
    public ModelAndView getAdminProductPage() {
        ModelAndView mav = new ModelAndView("admin/product");
        mav.addObject("products", productService.getAllProducts());
        return mav;
    }
    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // returns product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // returns page to add products
    @GetMapping("/create")
    public ModelAndView getAddProductPage() {
        ModelAndView mav = new ModelAndView("admin/addProduct");
        mav.addObject("product", new Product());
        mav.addObject("allCategories", categoryService.getAllCategories());
        return mav;
    }
    @PostMapping
    public RedirectView createProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return new RedirectView("/admin/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getUpdateProductPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/editProduct");
        Product product = productService.getProductById(id);
        mav.addObject("product", product);
        mav.addObject("allCategories", categoryService.getAllCategories());
        return mav;
    }

    @PostMapping("/edit/{id}")
    public RedirectView updateProduct(@PathVariable("id") Integer id, @ModelAttribute Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new RedirectView("/admin/product");
    }

    // method for deleting product, and reloading page
    @DeleteMapping("/{id}")
    public RedirectView deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new RedirectView("/admin/product");
    }
}