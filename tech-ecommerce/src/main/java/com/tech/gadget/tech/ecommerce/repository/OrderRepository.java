package com.tech.gadget.tech.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.gadget.tech.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCartId(String cartId);
}