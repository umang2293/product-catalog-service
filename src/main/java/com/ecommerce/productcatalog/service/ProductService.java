package com.ecommerce.productcatalog.service;

import com.ecommerce.productcatalog.dto.ProductDto;
import com.ecommerce.productcatalog.entity.Product;
import com.ecommerce.productcatalog.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Producer producer;

    public ProductService(ProductRepository productRepository,
                          Producer producer) {
        this.productRepository = productRepository;
        this.producer = producer;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(ProductDto productDto) throws JsonProcessingException {
        Product prod = new Product();
        prod.setName(productDto.getName());
        prod.setDescription(productDto.getDescription());
        prod.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        prod.setQuantity(productDto.getQuantity());
        producer.publishProductEvent(0);
        return productRepository.save(prod);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProductById(id);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
