package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
}