package com.gamestation.ecommerce.repository;

import com.gamestation.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The OrderRepository interface provides methods for accessing order data in the database.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


}
