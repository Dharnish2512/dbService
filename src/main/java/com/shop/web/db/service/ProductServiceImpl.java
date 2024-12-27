package com.shop.web.db.service;

import com.shop.web.db.entity.Product;
import com.shop.web.db.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ExcelToObjectService excelToObjectService;

    @Autowired
    ProductRepository productRepository;

    public PageImpl<Product> getProductList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return new PageImpl<>(products.getContent(),products.getPageable(),products.getTotalElements());
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

    @Override
    public Product getProductList(String productId) {
        return productRepository.findById(productId).get();
    }

    public List<Product> saveProduct(final List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public PageImpl<Product> getProductByText(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String regex = ".*" + query + ".*";
        return productRepository.findByNameContaining(regex, pageable);
    }
}
