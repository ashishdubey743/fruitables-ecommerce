package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.migrations.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
    List<User> findAll();
}