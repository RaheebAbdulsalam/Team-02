package com.example.demo.model;

import java.util.list;

import com.example.demo.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@Transactional
public class ShoppingCartServices {
    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<CartItem> listCartItems(Customer customer){
        return cartRepo.findByCustomer(customer);
    }

    public Integer addProduct (Integer productId, Integer quantity, Customer customer){
        Integer addedQuantity = quantity;
        productRepo.findByID(productId).get();

        CartItem cartItem = cartRepo.findByCustomerAndProduct(customer,product);

        if (cartItem != null){
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);

        }

        cartRepo.save(cartItem);

        return addedQuantity;
    }
@PostMapping("/cart/update{pid}/{qty}")
    public String updateQuantity(PathVariable("pid") Integer prductID, @PathVariable("qty") Integer quantity,
    System.out.println("addProductToCart: + " + productID +  quantity);
    @AuthenticationPrincipal Authentication authentication {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "you must login to shopping cart";
        }
        Customer customer = customerService.getCurrentlyLoggedInCustomer(Authentication);

        float subTotal = cartServices.updateQuantity(productID, quantity, customer);

        return String.valueOf(subTotal);
    }

    public float updateQuantity(Integer productId, Integer quantity, Customer customer){
        cartRepo.updateQuantity(quantity,productId,customer.getId());
        Product product = productRepo.findById(productId).get();


        float subtotal = product.getPrice() * quantity;
        return subtotal;

    }

    public void removeProduct(Integer productId,Customer customer){
        cartRepo.deleteByCustomerAndProduct(customer.getId(),productId);
    }

}
