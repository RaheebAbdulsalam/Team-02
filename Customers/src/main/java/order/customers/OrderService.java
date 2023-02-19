package order.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles business logic for managing orders.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    /**
     * Retrieves all orders from the database.
     *
     * @return a list of orders.
     */
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAllByOrderByOrderIdAsc();
        System.out.println("Retrieved " + orders.size() + " orders from the database.");
        return orders;
    }

    /**
     * Retrieves an order with the given ID from the database.
     *
     * @param orderId the ID of the order to retrieve.
     * @return the order with the given ID, or null if not found.
     */

    public Order getOrderById(Long orderId) {
         Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    /**
     * Creates a new order in the database.
     *
     * @param order the order to create.
     * @return the created order.
     */

    public Order createOrder(Order order) {
        Order createdOrder = orderRepository.save(order);
        System.out.println("Created order with ID " + createdOrder.getOrderId());
        return createdOrder;

    }

    /**
     * Updates an existing order in the database.
     *
     * @param orderId the ID of the order to update.
     * @param order   the updated order data.
     * @return the updated order, or null if the order was not found.
     */

    public Order updateOrder(Long orderId, Order order) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setProductId(order.getProductId());
            existingOrder.setEmail(order.getEmail());
            existingOrder.setTotal(order.getTotal());
            existingOrder.setStatus(order.getStatus());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    /**
     * Deletes an order with the given ID from the database.
     *
     * @param orderId the ID of the order to delete.
     * @return true if the order was deleted, false if the order was not found.
     */

    public boolean deleteOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return true;
        }
        return false;
    }
}