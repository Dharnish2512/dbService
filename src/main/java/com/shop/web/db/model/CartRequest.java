package com.shop.web.db.model;

import com.shop.web.db.entity.Cart;
import com.shop.web.db.entity.CartItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartRequest {
    String userId;
    CartItem cartItem;

    public static Cart convertToCart(final CartRequest cartRequest) {
        Cart cart = new Cart();
        cart.setUserId(cartRequest.getUserId());
        cart.setCartItems(List.of(cartRequest.getCartItem()));
        return cart;
    }
}
