package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.ShoppingCart;
import com.gamestation.ecommerce.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated() and #userId == principal.userId")
    public ModelAndView showShoppingCart(@PathVariable("userId") Integer userId) {
        List<ShoppingCart> shoppingCartItems = shoppingCartService.findByUserId(userId);
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("shoppingCartItems", shoppingCartItems);
        BigDecimal cartTotal = BigDecimal.ZERO;
        for (ShoppingCart item : shoppingCartItems) {
            BigDecimal itemPrice = item.getProduct().getPrice();
            BigDecimal itemQuantity = new BigDecimal(item.getQuantity());
            BigDecimal itemTotal = itemPrice.multiply(itemQuantity);
            cartTotal = cartTotal.add(itemTotal);
        }
        modelAndView.addObject("cartTotal", cartTotal);
        return modelAndView;
    }

    @PostMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> addToCart(@PathVariable("userId") Integer userId,
                                          @RequestParam("productId") Integer productId,
                                          @RequestParam("quantity") Integer quantity) {
        shoppingCartService.addToCart(userId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{cartItemId}")
    @PreAuthorize("isAuthenticated()")
    public RedirectView removeFromCart(@PathVariable("cartItemId") Integer cartItemId, HttpServletRequest request) {
        shoppingCartService.removeFromCart(cartItemId);
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }

    @PutMapping("/{cartItemId}")
    @PreAuthorize("isAuthenticated()")
    public RedirectView updateCartItem(@PathVariable("cartItemId") Integer cartItemId,
                                               @RequestParam("quantity") Integer quantity, HttpServletRequest request) {
        shoppingCartService.updateCartItem(cartItemId, quantity);
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }

}