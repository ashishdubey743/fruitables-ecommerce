package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.migrations.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

    List<Product> findAll();

    boolean existsByProductName(String productName);
    Optional<Product> findById(Integer id);
    boolean existsBySku(String name);

    boolean existsByProductNameAndIdNot(String name, Integer id);

    long countByCategory(String category);
    // List<Product> findByCategory(String category);
    List<Product> findByCategory(String category);
}
