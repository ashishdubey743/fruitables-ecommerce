package com.example.demo.model.entity.migrations;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Category {
    public Category(){
        this.status = '0';
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private String name;

    private String description;

    private char status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    
    private Long productCount;

    // Getters and Setters

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Long getProductCount(){
        return this.productCount;
    }

    public void setProductCount(Long productCount){
        this.productCount = productCount;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String Name){
        this.name = Name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String Desc){
        this.description = Desc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public char getStatus(){
        return this.status;
    }

    public void setStatus(char status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
