package com.example.demo.model.entity.migrations;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import javax.sql.rowset.serial.SerialBlob;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String category;
    private Integer productId;

    @Lob
    private Blob image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    // Getters and Setters

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Blob getImage() {
        return this.image;
    }

    public void setImage(byte[] bytes) throws SerialException, SQLException {
        // this.image = image;
                // this.image = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(bytes);
                this.image = new SerialBlob(bytes);
    }
}
