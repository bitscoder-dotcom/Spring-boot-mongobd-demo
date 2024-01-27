package com.bitscoder.mongodemo.service;

import com.bitscoder.mongodemo.exception.ResourceNotFoundException;
import com.bitscoder.mongodemo.product.Product;
import com.bitscoder.mongodemo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Product modifyProduct(String id, Product newProductData){
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProductData.getName());
                    product.setDescription(newProductData.getDescription());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: "+ id));
    }
}
