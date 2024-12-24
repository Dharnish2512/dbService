package com.shop.web.db.controller;

import com.shop.web.db.entity.Cart;
import com.shop.web.db.model.CartRequest;
import com.shop.web.db.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart/retrieve")
    public ResponseEntity<Cart> getCart(@RequestParam String userId) {
        Cart cart = cartService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<Cart> addProductsInCart(@RequestBody CartRequest cartRequest) {
        Cart cart = cartService.addProductInCart(cartRequest);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/update/quantity")
    public ResponseEntity<Cart> updateProductQuantity(@RequestParam String userId,
                                                      @RequestParam String productId,
                                                      @RequestParam int quantity) {
        Cart cart = cartService.updateProductQuantity(userId, productId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/cart/delete")
    public ResponseEntity<Cart> deleteProductById(@RequestParam String userId, @RequestParam String productId) {
        Cart cart = cartService.deleteProductsInCart(userId, productId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/cart/clear")
    public ResponseEntity<String> clearCart(@RequestParam String userId) {
        cartService.deleteCart(userId);
        return new ResponseEntity<>("Cart Cleared", HttpStatus.OK);
    }
}
