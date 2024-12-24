package com.shop.web.db.service;

import com.mongodb.client.MongoClient;
import com.shop.web.db.entity.Product;
import com.shop.web.db.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.AggregateIterable;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ExcelToObjectService excelToObjectService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoConverter mongoConverter;

    @Autowired
    MongoClient mongoClient;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).get();
        BeanUtils.copyProperties(product, existingProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public List<Product> parseExcelFile(InputStream inputStream) {
        List<Product> products = excelToObjectService.parseExcelFile(inputStream);
        productRepository.saveAll(products);
        return products;
    }

    public List<Product> saveProduct(final List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> getProductByText(String query) {
        List<Product> products = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("shop-db");
        MongoCollection<Document> collection = database.getCollection("product");
        AggregateIterable<Document> result = collection.aggregate(List.of(new Document("$search",
                new Document("index", "productIdIndex")
                        .append("text",
                                new Document("query", query)
                                        .append("path", "name")))));
        result.forEach(document -> products.add(mongoConverter.read(Product.class, document)));
        return products;
    }
}
