package com.tech.gadget.tech.ecommerce.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.tech.gadget.tech.ecommerce.entity.CartItem;
import com.tech.gadget.tech.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://tech-gadgets-store-psi.vercel.app"
	})public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    // =========================
    // GET LOGGED-IN USER CART
    // =========================

    @GetMapping
    public List<CartItem> getCart(
            Authentication authentication) {

        String username = authentication.getName();

        return cartService.getCart(username);
    }


    // =========================
    // ADD TO CART
    // =========================

    @PostMapping("/add/{productId}")
    public CartItem addToCart(
            Authentication authentication,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1")
            Integer quantity) {

        String username = authentication.getName();

        return cartService.addToCart(
                username,
                productId,
                quantity
        );
    }


    // =========================
    // UPDATE QUANTITY
    // =========================

    @PutMapping("/update/{productId}")
    public CartItem updateQuantity(
            Authentication authentication,
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        String username = authentication.getName();

        return cartService.updateQuantity(
                username,
                productId,
                quantity
        );
    }


    // =========================
    // REMOVE PRODUCT
    // =========================

    @DeleteMapping("/remove/{productId}")
    public String removeFromCart(
            Authentication authentication,
            @PathVariable Long productId) {

        String username = authentication.getName();

        cartService.removeFromCart(
                username,
                productId
        );

        return "Product removed from cart";
    }


    // =========================
    // CLEAR CART
    // =========================

    @DeleteMapping("/clear")
    public String clearCart(
            Authentication authentication) {

        String username = authentication.getName();

        cartService.clearCart(username);

        return "Cart cleared";
    }
}
