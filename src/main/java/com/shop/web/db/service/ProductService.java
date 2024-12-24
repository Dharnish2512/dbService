package com.shop.web.db.service;

import com.shop.web.db.entity.Product;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    List<Product> getProductList();

    void deleteProduct(String productId);

    List<Product> saveProduct(List<Product> product);

    List<Product> getProductByText(String query);

    Product updateProduct(Product product);

    List<Product> parseExcelFile(InputStream inputStream);
}
