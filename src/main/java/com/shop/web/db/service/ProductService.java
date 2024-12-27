package com.shop.web.db.service;

import com.shop.web.db.entity.Product;
import org.springframework.data.domain.PageImpl;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    PageImpl<Product> getProductList(int page, int size);

    void deleteProduct(String productId);

    List<Product> saveProduct(List<Product> product);

    PageImpl<Product> getProductByText(String query, int page, int size);

    Product updateProduct(Product product);

    List<Product> parseExcelFile(InputStream inputStream);

    Product getProductList(String productId);
}
