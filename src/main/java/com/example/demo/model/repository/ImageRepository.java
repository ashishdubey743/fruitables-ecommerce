package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.migrations.Image;

public interface ImageRepository extends CrudRepository<Image, Integer>{

}
