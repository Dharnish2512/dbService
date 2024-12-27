package com.shop.web.db.repository;

import com.shop.web.db.entity.Product;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    PageImpl<Product> findByNameContaining(String partialName, Pageable pageable);

}
