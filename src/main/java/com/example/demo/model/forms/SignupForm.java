package com.example.demo.model.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupForm {

    @NotBlank(message = "Username must not be blank")
    private String Username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String Email;

    @NotBlank(message = "Country must not be blank")
    private String  Country;

    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    private String Password;

    @NotNull(message = "You must agree to the terms and conditions")
    private String Terms;

    //Getters and Setters
    public String getUsername(){
        return this.Username;
    }

    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getEmail(){
        return this.Email;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }

    public String getCountry(){
        return this.Country;
    }

    public void setCountry(String Country){
        this.Country = Country;
    }

    public String getPassword(){
        return this.Password;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public String getTerms(){
        return this.Terms;
    }

    public void setTerms(String Terms){
        this.Terms = Terms;
    }
}
