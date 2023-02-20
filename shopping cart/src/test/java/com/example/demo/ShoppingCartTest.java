package com.example.demo;

import java.util.list;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.none)
@Rollback (false)
public class ShoppingCartTest {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Product product = entityManager.find(Product.class, 1);
        Customer customer = entityManager.find(Customer.class, 1);

        CartItem newItem = new CartItem();
        newItem.setCustomer(customer);
        newItem.setProduct(product);
        newItem.setQuantity(1);

        CartItem saveCartItem = cartRepo.save(newItem);

        assertTrue(saveCartItem.getID() > 0);

    }

    @Test
    public void testGetCartItemsByCustomer() {
        Customer customer = new Customer();
        customer.setId(5);

        List<CartItem> cartItem = cartRepo.findByCustomer(customer);

        assertEqual(2,cartItem.size());


    }
}
