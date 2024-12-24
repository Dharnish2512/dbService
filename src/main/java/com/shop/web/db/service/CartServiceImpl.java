package com.shop.web.db.service;

import com.shop.web.db.entity.Cart;
import com.shop.web.db.model.CartRequest;
import com.shop.web.db.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomMongoTemplate customMongoTemplate;

    @Override
    public Cart addProductInCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        Optional<Cart> cartOptional = cartRepository.findById(cartRequest.getUserId());
        if (cartOptional.isPresent()) {
            boolean itemExists = cartOptional.get().getCartItems().stream()
                    .anyMatch(item -> item.getProductId().equals(cartRequest.getCartItem().getProductId()));

            if (!itemExists) {
                cartOptional.get().getCartItems().add(cartRequest.getCartItem());
                cart = cartRepository.save(cartOptional.get());
            } else {
                log.info("Item is already in the cart");
            }
        } else {
            cart = cartRepository.save(CartRequest.convertToCart(cartRequest));
        }
        return cart;
    }

    @Override
    public Cart deleteProductsInCart(String userId, String productIds) {
        return customMongoTemplate.deleteProductInCart(userId, productIds);
    }

    @Override
    public void deleteCart(String userId) {
        cartRepository.deleteById(userId);
    }

    @Override
    public Cart updateProductQuantity(String userId, String productId, int newQuantity) {
        return customMongoTemplate.updateItemQuantity(userId, productId, newQuantity);
    }

    @Override
    public Cart getCart(String userId) {
        return cartRepository.findById(userId).get();
    }
}
