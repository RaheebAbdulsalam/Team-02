package com.gamestation.ecommerce.service;

import com.gamestation.ecommerce.model.Order;
import com.gamestation.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service class that handles business logic for managing orders.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * Retrieves all orders from the database.
     *
     * @return a list of orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order with the given ID from the database.
     *
     * @param orderId the ID of the order to retrieve.
     * @return the order with the given ID, or null if not found.
     */

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    /**
     * Creates a new order in the database.
     *
     * @param name the name of the order.
     * @param userId the ID of the user associated with the order.
     * @param order the details of the order to create.
     * @return the created order.
     */

    public Order createOrder(Order order, String name, Integer userId) {
        order.setName(name);
        order.setUserId(userId);
        return orderRepository.save(order);
    }



    /**
     * Updates an existing order in the database.
     *
     * @param orderId the ID of the order to update.
     * @return the updated order
     */

    public Order updateOrder(Integer orderId, Order orderDetails) {
        Order order = getOrderById(orderId);
        order.setEmail(orderDetails.getEmail());
        order.setTotal(orderDetails.getTotal());
        order.setStatus(orderDetails.getStatus());
        order.setName(orderDetails.getName());
        order.setUserId(orderDetails.getUserId());
        return orderRepository.save(order);
    }

    /**
     * Deletes an order with the given ID from the database.
     *
     * @param orderId the ID of the order to delete.
     */

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }



}