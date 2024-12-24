package com.shop.web.db.service;

import com.shop.web.db.entity.WishList;
import com.shop.web.db.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    CustomMongoTemplate customMongoTemplate;

    @Override
    public WishList addProduct(String userId, String productId) {
        Optional<WishList> wishList = wishListRepository.findById(userId);
        if (wishList.isPresent()) {
            wishListRepository.addProductsToWishlist(userId, productId);
        } else {
            wishListRepository.save(WishList.convertToWishList(userId, productId));
        }
        return wishListRepository.findById(userId).get();
    }

    @Override
    public WishList deleteProduct(String userId, String productId) {
        return customMongoTemplate.removeProductFromWishlist(userId, productId);
    }

    @Override
    public void clearWishList(String userId) {
        wishListRepository.deleteById(userId);
    }

    @Override
    public WishList getAllFavorites(String userId) {
        return wishListRepository.findById(userId).get();
    }
}
