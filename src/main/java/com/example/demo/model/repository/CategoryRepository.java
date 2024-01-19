package com.example.demo.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.migrations.Category;
import com.example.demo.model.entity.migrations.Product;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
    public boolean existsByName(String name);
    List<Category> findAll();
    Optional<Category> findById(Integer id);
    public boolean existsByNameAndIdNot(String categoryName, Integer categoryId);
}