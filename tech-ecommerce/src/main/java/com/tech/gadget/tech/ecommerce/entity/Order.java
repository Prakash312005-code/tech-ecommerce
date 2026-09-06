
	package com.tech.gadget.tech.ecommerce.entity;

	import jakarta.persistence.*;
	import java.time.LocalDateTime;

	@Entity
	@Table(name = "orders")
	public class Order {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String cartId;

	    private String customerName;

	    private String email;

	    private String phone;

	    private String address;

	    private String city;

	    private String state;

	    private String pincode;

	    private Double totalAmount;

	    private LocalDateTime orderDate;

	    private String status;

	    public Order() {
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

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public String getPincode() {
	        return pincode;
	    }

	    public void setPincode(String pincode) {
	        this.pincode = pincode;
	    }

	    public Double getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(Double totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public LocalDateTime getOrderDate() {
	        return orderDate;
	    }

	    public void setOrderDate(LocalDateTime orderDate) {
	        this.orderDate = orderDate;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	}

