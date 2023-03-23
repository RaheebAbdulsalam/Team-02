package com.gamestation.ecommerce.repository;

import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Integer> {
    Sale findByProduct(Product product);
}
