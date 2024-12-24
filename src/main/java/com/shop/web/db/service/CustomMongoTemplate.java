package com.shop.web.db.service;

import com.shop.web.db.entity.Cart;
import com.shop.web.db.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CustomMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public WishList removeProductFromWishlist(String userId, String productId) {
        // Define the query to match the userId
        Query query = new Query(Criteria.where("_id").is(userId));
        // Define the update to remove the productId from the productIds array
        Update update = new Update().pull("productIds", productId);
        // Perform the update
        mongoTemplate.updateFirst(query, update, WishList.class);

        WishList wishList = mongoTemplate.findOne(query, WishList.class);
        if (wishList != null && (wishList.getProductIds() == null || wishList.getProductIds().isEmpty())) {
            mongoTemplate.remove(query, WishList.class);
            wishList = new WishList();
        }
        return wishList;
    }

    public Cart updateItemQuantity(String userId, String productId, int newQuantity) {
        Query query = new Query(Criteria.where("_id").is(userId)
                .and("cartItems.productId").is(productId));
        // Create the update operation to set the new quantity for the CartItem
        Update update = new Update().set("cartItems.$.quantity", newQuantity);
        // Perform the update operation
        mongoTemplate.updateFirst(query, update, Cart.class);
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(userId)), Cart.class);
    }

    public Cart deleteProductInCart(String userId, String productId) {
        Query query = new Query(Criteria.where("_id").is(userId));
        Update update = new Update().pull("cartItems", Query.query(new Criteria("productId").is(productId)));
        mongoTemplate.updateFirst(query, update, Cart.class);

        Cart cart = mongoTemplate.findOne(query, Cart.class);
        if (cart != null && (cart.getCartItems() == null || cart.getCartItems().isEmpty())) {
            // If no items are left in the cart, delete the cart
            mongoTemplate.remove(query, Cart.class);
            cart = new Cart();
        }
        return cart;
    }
}
