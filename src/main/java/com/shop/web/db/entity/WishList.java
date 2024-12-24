package com.shop.web.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "WishList")
public class WishList {

    @Id
    String userId;
    List<String> productIds;

    public static WishList convertToWishList(String userId, String productId) {
        WishList wishList = new WishList();
        wishList.setUserId(userId);
        wishList.setProductIds(List.of(productId));
        return wishList;
    }
}
