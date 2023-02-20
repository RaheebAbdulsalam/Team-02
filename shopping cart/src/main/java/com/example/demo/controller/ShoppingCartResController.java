package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartResController {
  @Autowired
    private ShoppingCartServices cartServices;
    @Autowired
    private CustomerServices customerService;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(PathVariable("pid") Integer prductID, @PathVariable("qty") Integer quantity,
    System.out.println("addProductToCart: + " + productID +  quantity);
    @AuthenticationPrincipal Authentication authentication ){
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "you must login to shopping cart";
        }
        Customer customer = customerService.getCurrentlyLoggedInCustomer(Authentication);

        Integer addedQuantity = cartServices.addProduct(productID, quantity, customer);

        return addedQuantity +  "item(s) of this product were added to your cart";
    }

    @PostMapping("/cart/remove/{pid}")
    public String removeProductFromCart(PathVariable("pid") Integer prductID, @PathVariable("qty") Integer quantity,
    System.out.println("addProductToCart: + " + productID +  quantity);
    @AuthenticationPrincipal Authentication authentication ){
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "you must login to update shopping cart";
        }
        Customer customer = customerService.getCurrentlyLoggedInCustomer(Authentication);

        Integer addedQuantity = cartServices.addProduct(productID, quantity, customer);

        cartServices.removeProduct(productId, customer);

        return  "item(s) of this product were removed to your cart";
    }

    public Integer getQuantity() {
        return quantity;
    }


}
