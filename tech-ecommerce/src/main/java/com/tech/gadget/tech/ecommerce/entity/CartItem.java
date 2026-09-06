package com.tech.gadget.tech.ecommerce.entity;

	import jakarta.persistence.*;

	@Entity
	@Table(name = "cart_items")
	public class CartItem {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String cartId;

	    @ManyToOne 
	    @JoinColumn(name = "product_id")
	    private Product product;

	    private Integer quantity;

	    public CartItem() {
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getCartId() {
	        return cartId;
	    }

	    public void setCartId(String cartId) {
	        this.cartId = cartId;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }
	}

