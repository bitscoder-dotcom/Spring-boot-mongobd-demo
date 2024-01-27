package com.bitscoder.mongodemo.controller;

import com.bitscoder.mongodemo.exception.ResourceNotFoundException;
import com.bitscoder.mongodemo.product.Product;
import com.bitscoder.mongodemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("productapi/v1/")
public class ProductController {

    private ProductService productService;

    @PostMapping("saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("findAllProducts")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("findProductById/{productId}")
    public ResponseEntity<Product> findById(@PathVariable String productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with product Id: "+ productId +" not found"));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product with product Id: " + productId + " has been deleted", HttpStatus.OK);
    }

    @PutMapping("modifyProduct/{productId}")
    public ResponseEntity<Product> modifyProduct(@PathVariable String productId, @RequestBody Product newProductData) {
        Product updatedProduct = productService.modifyProduct(productId, newProductData);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

}
