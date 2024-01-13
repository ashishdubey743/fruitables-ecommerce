package com.example.demo.model.forms;

import jakarta.validation.constraints.NotBlank;

public class AddCategoryForm {
    
    @NotBlank(message = "Category Name cannot be Blank")
    private String categoryName;

    @NotBlank(message = "Category Description cannot be Blank")
    private String categoryDescription;

    //Getters and Setters
    public String getCategoryName(){
        return this.categoryName;
    }

    public void setCategoryName(String catName){
        this.categoryName = catName;
    }

    public String getCategoryDescription(){
        return this.categoryDescription;
    }

    public void setCategoryDescription(String catDesc){
        this.categoryDescription = catDesc;
    }
}
