package com.gamestation.ecommerce.repository;

import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.model.ShoppingCart;
import com.gamestation.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findByUserId(Integer userId);

    ShoppingCart findByUserAndProduct(User user, Product product);
}