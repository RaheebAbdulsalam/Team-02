package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.*;
import com.gamestation.ecommerce.service.OrderService;
import com.gamestation.ecommerce.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    private BigDecimal totalPrice;

    @GetMapping
    public ModelAndView getShoppingCart(Authentication authentication) {
        // Users must log in to access cart - return to login page
        if (authentication == null) {
            return new ModelAndView("redirect:/login");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<ShoppingCart> cartItems = shoppingCartService.getCartItems(userDetails.getUser());

        totalPrice = BigDecimal.ZERO; // initialize the total price variable
        for (ShoppingCart cartItem : cartItems) {
            BigDecimal productPrice = cartItem.getProduct().getPrice();
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity()); // convert the Integer to a BigDecimal
            totalPrice = totalPrice.add(productPrice.multiply(quantity)); // calculate the total price
        }

        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("cartItems", cartItems);
        modelAndView.addObject("totalPrice", totalPrice); // add the total price to the model
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity, Authentication authentication) {
        // Users must log in to add to cart - return to login page
        if (authentication == null) {
            return ResponseEntity.ok("You must be logged in to make purchases.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        shoppingCartService.addToCart(userDetails.getUser(), productId, quantity);
        return ResponseEntity.ok("Item added successfully to cart.");
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<?> updateCartItemQuantity(@PathVariable Integer itemId, @RequestParam("quantity") Integer quantity, Authentication authentication, HttpServletResponse response) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        shoppingCartService.updateCartItemQuantity(userDetails.getUser(), itemId, quantity);
        response.setHeader("Location", "/shoppingcart");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Integer itemId, Authentication authentication, HttpServletResponse response) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        shoppingCartService.removeCartItem(userDetails.getUser(), itemId);
        response.setHeader("Location", "/shoppingcart");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(Authentication authentication) {
        // Users must log in to checkout - return to login page
        if (authentication == null) {
            return ResponseEntity.ok("You must be logged in to make purchases.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<ShoppingCart> cartItems = shoppingCartService.getCartItems(userDetails.getUser());

        // Check if the cart is empty
        if (cartItems.isEmpty()) {
            return ResponseEntity.ok("Your shopping cart is empty. Please add items to your cart before checking out.");
        }

        totalPrice = BigDecimal.ZERO; // initialize the total price variable
        for (ShoppingCart cartItem : cartItems) {
            BigDecimal productPrice = cartItem.getProduct().getPrice();
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity()); // convert the Integer to a BigDecimal
            totalPrice = totalPrice.add(productPrice.multiply(quantity)); // calculate the total price
        }

        Order order = new Order();
        order.setUserId(userDetails.getUser().getId());
        order.setName(userDetails.getFullName());
        order.setEmail(userDetails.getUser().getEmail());
        order.setTotal(totalPrice);
        order.setStatus("NEW");

        order = orderService.createOrder(order, userDetails.getFullName(), userDetails.getUser().getId());

        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCart cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity());
            orderItem.setOrder(order); // set the order ID
            orderItems.add(orderItem); // add the order item to the list
            orderItem.setStatus("NEW");
        }

        order.setOrderItems(orderItems); // set the order items for the order

        orderService.createOrder(order, userDetails.getFullName(), userDetails.getUser().getId());
        shoppingCartService.removeAllCartItems(userDetails.getUser().getId());

        return ResponseEntity.ok("Checkout successful.");
    }



}
