package com.example.demo.model.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddUserForm {
    

    @NotBlank(message = "Username must not be Blank")
    private String username;

    @NotBlank(message = "Email must not be Blank")
    private String email;

    @NotBlank(message = "Country must not be Blank")
    private String country;

    @NotBlank(message = "Password must not be Blank")
    private String password;

    @NotNull(message = "Please Check Terms and Conditions")
    private String terms;

    private MultipartFile image;

    private String address;

    public MultipartFile getImage(){
        return this.image;
    }
    public void setImage(MultipartFile image){
        this.image = image;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

}

