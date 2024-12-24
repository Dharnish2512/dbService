package com.shop.web.db.controller;

import com.shop.web.db.entity.Product;
import com.shop.web.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product/add")
    public List<Product> addProduct(@RequestBody List<Product> products) {
        return productService.saveProduct(products);
    }

    @PutMapping("/product/update")
    public Product addProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/product/getAll")
    public List<Product> getAllProducts() {
        return productService.getProductList();
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(
                "Product Removed!!", HttpStatus.OK);
    }

    @GetMapping("/product/{query}")
    public ResponseEntity<List<Product>> getProductWithQuery(@PathVariable String query) {
        return new ResponseEntity<>(productService.getProductByText(query), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Product>> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<Product> products = productService.parseExcelFile(file.getInputStream());
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
