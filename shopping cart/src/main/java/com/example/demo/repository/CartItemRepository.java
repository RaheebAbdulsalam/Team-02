package com.example.demo.repository;

import java.util.list;

import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

    public List<CartItem> findByCustomer(Customer customer);

    public CartItem findByCustomerAndProduct(Customer customer, Product product);

    @Query("Update CartItem c SET c.quantity = ?1 WHERE c.product.id = ?2" +
            "AND c.customer.id = ?3")
    @Modifying
    public void updateQuantity(Integer quantity,Integer productID, Integer customerID);

    @Query("DELETE FROM CartItem c WHERE c.customer.id = ?! AND c.product.id = ?2")
    @Modifying
    public void deleteByCustomerAndProduct(Integer customerId, Integer productId);

}