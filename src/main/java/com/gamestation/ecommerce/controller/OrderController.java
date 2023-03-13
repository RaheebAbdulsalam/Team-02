package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Order;
import com.gamestation.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller for handling REST API requests related to orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * GET endpoint for retrieving all orders.
     *
     * @return ResponseEntity with a list of Order objects and HTTP status OK (200) on success.
     */

    @GetMapping("/list")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * POST endpoint for creating a new order.
     *
     * @param order the Order object to be created.
     * @return ResponseEntity with the created Order object and HTTP status CREATED (201) on success.
     */

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @RequestParam String name, @RequestParam Integer userId) {
        Order createdOrder = orderService.createOrder(order, name, userId);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     * PUT endpoint for updating an existing order.
     *
     * @param orderId the ID of the order to be updated.
     * @return ResponseEntity with the updated Order object and HTTP status OK (200) on success, or HTTP status NOT_FOUND (404) if the order was not found.
     */
    @PutMapping("/{order_id}")
    public ResponseEntity<Order> updateOrder(@PathVariable ("order_id") Integer orderId, @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(orderId, orderDetails);
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
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("order_id") Integer orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping()
    public ModelAndView ordersPage() {
        ModelAndView mav = new ModelAndView("admin/orders");
        mav.addObject("orders", orderService.getAllOrders());
        return mav;
    }


}
