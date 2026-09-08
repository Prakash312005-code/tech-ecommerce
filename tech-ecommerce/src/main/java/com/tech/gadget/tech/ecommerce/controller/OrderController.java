package com.tech.gadget.tech.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.gadget.tech.ecommerce.entity.Order;
import com.tech.gadget.tech.ecommerce.entity.OrderItem;
import com.tech.gadget.tech.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://tech-gadgets-store-psi.vercel.app"
	})public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place Order
    @PostMapping("/{cartId}")
    public Order createOrder(
            @PathVariable String cartId,
            @RequestParam String customerName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String pincode) {

        return orderService.createOrder(
                cartId,
                customerName,
                email,
                phone,
                address,
                city,
                state,
                pincode
        );
    }

    // Get all orders
    @GetMapping("/{cartId}")
    public List<Order> getOrders(
            @PathVariable String cartId) {

        return orderService.getOrders(cartId);
    }

    // Get one order
    @GetMapping("/details/{orderId}")
    public Order getOrderById(
            @PathVariable Long orderId) {

        return orderService.getOrderById(orderId);
    }

    // Get order items
    @GetMapping("/details/{orderId}/items")
    public List<OrderItem> getOrderItems(
            @PathVariable Long orderId) {

        return orderService.getOrderItems(orderId);
    }

    // Update order tracking status
    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        return orderService.updateOrderStatus(
                orderId,
                status
        );
    }

    // Cancel order
    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(
            @PathVariable Long orderId) {

        return orderService.cancelOrder(orderId);
    }
}
