package com.shop.web.db.service;

import com.shop.web.db.entity.Cart;
import com.shop.web.db.model.CartRequest;

public interface CartService {
    Cart addProductInCart(CartRequest cartRequest);

    Cart deleteProductsInCart(String userId, String productIds);

    void deleteCart(String userId);

    Cart updateProductQuantity(String userId, String productId, int newQuantity);

    Cart getCart(String userId);
}
