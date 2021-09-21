package com.sapient.order.repository;

import com.sapient.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM product ORDER BY rand() LIMIT 1")
    List<Product> findRandamProduct();

}