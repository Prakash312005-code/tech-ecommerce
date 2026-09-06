package com.tech.gadget.tech.ecommerce.repository;



	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.tech.gadget.tech.ecommerce.entity.CartItem;

	public interface CartRepository extends JpaRepository<CartItem, Long> {

	    List<CartItem> findByCartId(String cartId);

	    CartItem findByCartIdAndProductId(
	            String cartId,
	            Long productId
	    );
	}

