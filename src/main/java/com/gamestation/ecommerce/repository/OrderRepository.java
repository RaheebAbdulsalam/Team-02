package order.customers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * The OrderRepository interface provides methods for accessing order data in the database.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Returns a list of all orders in ascending order of their order ID.
     * @return A list of all orders.
    */

    List<Order> findAllByOrderByOrderIdAsc();

    /**
     * Returns an optional Order object that matches the given order ID.
     * @param orderId The order ID to search for.
     * @return An optional Order object that matches the given ID.
     */

    Optional<Order> findByOrderId(Long orderId);
}
