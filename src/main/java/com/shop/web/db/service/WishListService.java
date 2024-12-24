package com.shop.web.db.service;

import com.shop.web.db.entity.WishList;

public interface WishListService {
    WishList addProduct(String userId, String productId);

    WishList deleteProduct(String userId, String productId);

    void clearWishList(String userId);

    WishList getAllFavorites(String userId);
}
