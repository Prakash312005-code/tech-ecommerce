package com.tech.gadget.tech.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.gadget.tech.ecommerce.entity.CartItem;
import com.tech.gadget.tech.ecommerce.entity.Order;
import com.tech.gadget.tech.ecommerce.entity.OrderItem;
import com.tech.gadget.tech.ecommerce.entity.Product;
import com.tech.gadget.tech.ecommerce.repository.CartRepository;
import com.tech.gadget.tech.ecommerce.repository.OrderItemRepository;
import com.tech.gadget.tech.ecommerce.repository.OrderRepository;
// this service do both order creation and order tracking. 
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
    }

    // Create Order from Cart
    public Order createOrder(
            String cartId,
            String customerName,
            String email,
            String phone,
            String address,
            String city,
            String state,
            String pincode) {

        // Get cart items
        List<CartItem> cartItems = cartRepository.findByCartId(cartId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Create Order
        Order order = new Order();

        order.setCartId(cartId);
        order.setCustomerName(customerName);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setPincode(pincode);
        order.setOrderDate(LocalDateTime.now());

        // Initial tracking status
        order.setStatus("PLACED");

        // Calculate total
        double totalAmount = 0;

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();

            totalAmount +=
                    product.getPrice() * cartItem.getQuantity();
        }

        order.setTotalAmount(totalAmount);

        // Save Order first
        Order savedOrder = orderRepository.save(order);

        // Create Order Items
        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setProductName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());

            orderItemRepository.save(orderItem);
        }

        // Clear cart after successful order
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    // Get all orders for a cart/user
    public List<Order> getOrders(String cartId) {

        return orderRepository.findByCartId(cartId);
    }

    // Get single order
    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    // Get order items
    public List<OrderItem> getOrderItems(Long orderId) {

        return orderItemRepository.findByOrderId(orderId);
    }

    // Update order tracking status
    public Order updateOrderStatus(
            Long orderId,
            String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }

    // Cancel order
    public Order cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus("CANCELLED");

        return orderRepository.save(order);
    }
}