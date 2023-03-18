package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Order;
import com.gamestation.ecommerce.model.OrderItem;
import com.gamestation.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

/**
 * Controller for handling REST API requests related to orders.
 */
@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ModelAndView ordersPage(@RequestParam(name = "status", required = false) String status) {
        ModelAndView mav = new ModelAndView("admin/order/orders");
        List<Order> orders;
        if ("new".equals(status)) {
            orders = orderService.getOrdersByStatus("NEW");
        } else if ("cancelled".equals(status)) {
            orders = orderService.getOrdersByStatus("CANCELLED");
        } else if ("accepted".equals(status)) {
            orders = orderService.getOrdersByStatus("ACCEPTED");
        } else if ("shipped".equals(status)) {
            orders = orderService.getOrdersByStatus("SHIPPED");
        } else if ("completed".equals(status)) {
            orders = orderService.getOrdersByStatus("COMPLETED");
        } else {
            orders = orderService.getAllOrders();
        }
        mav.addObject("orders", orders);
        return mav;
    }

    @GetMapping("/show/{orderId}")
    public ModelAndView showOrder(@PathVariable Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        ModelAndView mav = new ModelAndView("admin/order/orderDetails");
        mav.addObject("order", order);
        return mav;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @RequestParam String name, @RequestParam Integer userId) {
        Order createdOrder = orderService.createOrder(order, name, userId);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{order_id}")
    public ResponseEntity<Order> updateOrder(@PathVariable ("order_id") Integer orderId, @RequestParam String status, HttpServletResponse response) throws IOException {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setStatus(status);
        orderService.updateOrder(orderId, order);
        response.sendRedirect("/admin/order");
        return null;
    }

    @PutMapping("/show/{order_id}")
    public ResponseEntity<Order> updateShowOrder(@PathVariable ("order_id") Integer orderId, @RequestParam String status, HttpServletResponse response) throws IOException {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setStatus(status);
        orderService.updateOrder(orderId, order);
        response.sendRedirect("/admin/order/show/" + orderId);
        return null;
    }

    @PutMapping("/show/{orderId}/{itemId}")
    public ResponseEntity<?> updateOrderItemStatus(@PathVariable Integer orderId, @PathVariable Integer itemId, @RequestParam String status, HttpServletResponse response) throws IOException {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        OrderItem orderItem = null;
        for (OrderItem item: order.getOrderItems()) {
            if (item.getId() == itemId){
                orderItem = item;
            }
        }

        if (orderItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderItem.setStatus(status);
        orderService.updateOrderItem(orderId, order, itemId); // pass in the itemId to update the specific OrderItem
        response.sendRedirect("/admin/order/show/" + orderId);
        return null;
    }


    @DeleteMapping("/{order_id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("order_id") Integer orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
