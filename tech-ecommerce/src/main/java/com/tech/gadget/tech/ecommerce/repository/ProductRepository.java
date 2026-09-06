package com.tech.gadget.tech.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.gadget.tech.ecommerce.entity.Product;

				
	public interface ProductRepository extends JpaRepository<Product, Long> {

}