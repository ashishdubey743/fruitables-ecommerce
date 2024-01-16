package com.example.demo.model.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotBlank(message = "Please Insert an Image")
    private String image;

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

    public String getImage(){
        return this.image;
    }
    public void setImage(String image){
        this.image = image;
    }
}
