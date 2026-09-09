package com.tech.gadget.tech.ecommerce.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.tech.gadget.tech.ecommerce.entity.Order;
import com.tech.gadget.tech.ecommerce.entity.OrderItem;
import com.tech.gadget.tech.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://tech-gadgets-store-psi.vercel.app"
})
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // PLACE ORDER
    @PostMapping
    public Order createOrder(
            Authentication authentication,
            @RequestParam String customerName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String pincode) {

        String username = authentication.getName();

        return orderService.createOrder(
                username,
                customerName,
                email,
                phone,
                address,
                city,
                state,
                pincode
        );
    }

    // GET ORDERS
    @GetMapping
    public List<Order> getOrders(Authentication authentication) {

        String username = authentication.getName();

        return orderService.getOrders(username);
    }

    // GET ONE ORDER
    @GetMapping("/details/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {

        return orderService.getOrderById(orderId);
    }

    // GET ORDER ITEMS
    @GetMapping("/details/{orderId}/items")
    public List<OrderItem> getOrderItems(
            @PathVariable Long orderId) {

        return orderService.getOrderItems(orderId);
    }

    // UPDATE STATUS
    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        return orderService.updateOrderStatus(
                orderId,
                status
        );
    }

    // CANCEL ORDER
    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable Long orderId) {

        return orderService.cancelOrder(orderId);
    }
}
