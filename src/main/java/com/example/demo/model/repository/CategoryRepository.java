package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.migrations.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
    // public boolean existsByname(String name);
    public boolean existsByName(String name);
    List<Category> findAll();
}