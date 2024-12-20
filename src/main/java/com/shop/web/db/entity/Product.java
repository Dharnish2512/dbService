package com.shop.web.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "product")
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String productCode;
    private String name;
    private String imagePath;
    private String category;
    private String description;
    private BigDecimal price;

}
