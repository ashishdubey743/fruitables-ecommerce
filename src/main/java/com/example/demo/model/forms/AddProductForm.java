package com.example.demo.model.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddProductForm {

    private Integer id;

    @NotBlank(message = "Product Name cannot be Blank")
    private String name;

    @NotNull(message = "Quantity cannot be Blank")
    private Integer quantity;

    @NotBlank(message = "SKU cannot be Blank")
    private String sku;

    @NotBlank(message = "Category cannot be Blank")
    private String category;

    @NotNull(message = "Please Insert an Image")
    private MultipartFile image;

    // Getters and Setters

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Integer getQuantity(){
        return this.quantity;
    }
    public void setQuantity(Integer qty){
        this.quantity = qty;
    }

    public String getSku(){
        return this.sku;
    }
    public void setSku(String sku){
        this.sku = sku;
    }

    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public MultipartFile getImage(){
        return this.image;
    }
    public void setImage(MultipartFile image){
        this.image = image;
    }
}
