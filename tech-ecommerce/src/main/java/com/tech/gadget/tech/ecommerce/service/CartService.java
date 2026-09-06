package com.tech.gadget.tech.ecommerce.service;


	

	import java.util.List;

	import org.springframework.stereotype.Service;

	import com.tech.gadget.tech.ecommerce.entity.CartItem;
	import com.tech.gadget.tech.ecommerce.entity.Product;
	import com.tech.gadget.tech.ecommerce.repository.CartRepository;
	import com.tech.gadget.tech.ecommerce.repository.ProductRepository;

	@Service
	public class CartService {

	    private final CartRepository cartRepository;
	    private final ProductRepository productRepository;

	    public CartService(
	            CartRepository cartRepository,
	            ProductRepository productRepository) {

	        this.cartRepository = cartRepository;
	        this.productRepository = productRepository;
	    }

//get cart
	    public List<CartItem> getCart(String cartId) {

	        return cartRepository.findByCartId(cartId);
	    }


	 //add to cart
	    public CartItem addToCart(
	            String cartId,
	            Long productId,
	            Integer quantity) {

	        Product product = productRepository
	                .findById(productId)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));


	        CartItem existingItem =
	                cartRepository.findByCartIdAndProductId(
	                        cartId,
	                        productId
	                );


	        // Product already exists in cart
	        if (existingItem != null) {

	            existingItem.setQuantity(
	                    existingItem.getQuantity() + quantity
	            );

	            return cartRepository.save(existingItem);
	        }


	        // New cart item
	        CartItem cartItem = new CartItem();

	        cartItem.setCartId(cartId);
	        cartItem.setProduct(product);
	        cartItem.setQuantity(quantity);

	        return cartRepository.save(cartItem);
	    }

//update quantity
	    public CartItem updateQuantity(
	            String cartId,
	            Long productId,
	            Integer quantity) {

	        CartItem existingItem =
	                cartRepository.findByCartIdAndProductId(
	                        cartId,
	                        productId
	                );


	        if (existingItem == null) {

	            throw new RuntimeException(
	                    "Product not found in cart"
	            );
	        }


	        // If quantity becomes 0,
	        // remove the item
	        if (quantity <= 0) {

	            cartRepository.delete(existingItem);

	            return null;
	        }


	        existingItem.setQuantity(quantity);

	        return cartRepository.save(existingItem);
	    }


	//REMOVE FROM CART
	    public void removeFromCart(
	            String cartId,
	            Long productId) {

	        CartItem existingItem =
	                cartRepository.findByCartIdAndProductId(
	                        cartId,
	                        productId
	                );


	        if (existingItem != null) {

	            cartRepository.delete(existingItem);
	        }
	    }

//clear cart
	    
	    public void clearCart(String cartId) {

	        List<CartItem> items =
	                cartRepository.findByCartId(cartId);

	        cartRepository.deleteAll(items);
	    }
	}
