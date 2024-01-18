package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.example.demo.model.entity.migrations.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

    List<Product> findAll();
    
}
