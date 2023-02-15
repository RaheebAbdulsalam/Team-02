package com.gamestation.ecommerce.service;

import com.gamestation.ecommerce.exception.ResourceNotFoundException;
import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        product.setStockLevel(productDetails.getStockLevel());
        product.setImage(productDetails.getImage());
        product.setCategories(productDetails.getCategories());

        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
