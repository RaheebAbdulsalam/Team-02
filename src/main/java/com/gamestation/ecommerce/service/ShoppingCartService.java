package com.gamestation.ecommerce.service;

import com.gamestation.ecommerce.exception.ResourceNotFoundException;
import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.model.ShoppingCart;
import com.gamestation.ecommerce.model.User;
import com.gamestation.ecommerce.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<ShoppingCart> findByUserId(Integer userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    public ShoppingCart findById(Integer id) {
        return shoppingCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public void addToCart(Integer userId, Integer productId, Integer quantity) {
        User user = userService.get(userId);
        Product product = productService.getProductById(productId);
        ShoppingCart cartItem = shoppingCartRepository.findByUserAndProduct(user, product);

        if (cartItem == null) {
            cartItem = new ShoppingCart(user, product, quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        shoppingCartRepository.save(cartItem);
    }

    public void removeFromCart(Integer cartItemId) {
        shoppingCartRepository.deleteById(cartItemId);
    }

    public void updateCartItem(Integer cartItemId, Integer quantity) {
        ShoppingCart cartItem = findById(cartItemId);
        cartItem.setQuantity(quantity);
        shoppingCartRepository.save(cartItem);
    }


}
