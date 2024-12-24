package com.shop.web.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cart")
@Getter
@Setter
public class Cart {
    @Id
    String userId;
    List<CartItem> cartItems;
}
