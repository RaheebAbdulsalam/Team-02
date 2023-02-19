package order.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling REST API requests related to orders.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * GET endpoint for retrieving all orders.
     *
     * @return ResponseEntity with a list of Order objects and HTTP status OK (200) on success.
     */

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println("All Orders: " + orders.toString());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * GET endpoint for printing all orders to the console.
     *
     * @return ResponseEntity with a success message and HTTP status OK (200) on success.
     */
    @GetMapping("/printOrders")
    public ResponseEntity<String> printOrders() {
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Product ID: " + order.getProductId());
            System.out.println("Email: " + order.getEmail());
            System.out.println("Total: " + order.getTotal());
            System.out.println("Status: " + order.getStatus());
            System.out.println("-----------");
        }
        return new ResponseEntity<>("Orders printed successfully", HttpStatus.OK);
    }

    /**
     * POST endpoint for creating a new order.
     *
     * @param order the Order object to be created.
     * @return ResponseEntity with the created Order object and HTTP status CREATED (201) on success.
     */

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        System.out.println("Couldn't create Order");
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     * PUT endpoint for updating an existing order.
     *
     * @param orderId the ID of the order to be updated.
     * @param order the updated Order object.
     * @return ResponseEntity with the updated Order object and HTTP status OK (200) on success, or HTTP status NOT_FOUND (404) if the order was not found.
     */
    @PutMapping("/{order_id}")
    public ResponseEntity<Order> updateOrder(@PathVariable ("order_id") Long orderId, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(orderId, order);
        if (updatedOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    /**
     * DELETE endpoint for deleting an existing order.
     *
     * @param orderId the ID of the order to be deleted.
     * @return ResponseEntity with HTTP status NO_CONTENT (204) on success, or HTTP status NOT_FOUND (404) if the order was not found.
     */
    @DeleteMapping("/{order_id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("order_id") Long orderId) {
        boolean deleted = orderService.deleteOrder(orderId);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}