package com.example.demo.controller;


import com.example.demo.model.CartItem;
import com.example.demo.model.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServices cartServices;

    private CustomerServices customerService;


    @GetMapping("/cart")
    public String showShoppingCart(Model model,
                                   @AuthenticationPrincipal Authentication authentication) {

        Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
        List<CartItem> cartItems = cartServices.listCartItems(customer);


        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", cartItems);

        return "shopping_cart";




    }
}
