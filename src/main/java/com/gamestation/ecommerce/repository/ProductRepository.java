package com.gamestation.ecommerce.repository;

import com.gamestation.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String query);

<<<<<<< HEAD
}
=======
}
>>>>>>> 740c47456dc6577b536504a905b1c6017e8663ac
