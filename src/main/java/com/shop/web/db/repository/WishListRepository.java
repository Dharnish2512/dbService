package com.shop.web.db.repository;

import com.shop.web.db.entity.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends MongoRepository<WishList, String> {
    @Query("{ '_id': ?0 }")
//    @Transactional
    @Update("{ '$addToSet': { 'productIds': ?1 } }")
    void addProductsToWishlist(String userId, String productId);
}
