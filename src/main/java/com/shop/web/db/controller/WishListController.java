package com.shop.web.db.controller;

import com.shop.web.db.entity.WishList;
import com.shop.web.db.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishListController {

    @Autowired
    WishListService wishListService;

    @GetMapping("/wishlist/retrieve")
    public ResponseEntity<WishList> getAllWishlist(@RequestParam String userId) {
        WishList allFavorites = wishListService.getAllFavorites(userId);
        return new ResponseEntity<>(allFavorites, HttpStatus.OK);
    }

    @PostMapping("/wishlist/add")
    public ResponseEntity<WishList> addFavoriteInCart(@RequestParam String userId,
                                                      @RequestParam String productId) {
        WishList wishList = wishListService.addProduct(userId, productId);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @DeleteMapping("/wishlist/delete")
    public ResponseEntity<WishList> deleteFavoriteByProductId(@RequestParam String userId,
                                                              @RequestParam String productId) {
        WishList wishList = wishListService.deleteProduct(userId, productId);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @DeleteMapping("/wishlist/clear")
    public ResponseEntity<String> clearFavorites(@RequestParam String userId) {
        wishListService.clearWishList(userId);
        return new ResponseEntity<>("Cart Cleared", HttpStatus.OK);
    }
}
