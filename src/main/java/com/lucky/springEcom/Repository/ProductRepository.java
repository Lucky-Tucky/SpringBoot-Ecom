package com.lucky.springEcom.Repository;

import com.lucky.springEcom.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
