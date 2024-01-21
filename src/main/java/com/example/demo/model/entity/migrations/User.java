package com.example.demo.model.entity.migrations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  private Integer id;
  private String Username;

  @Column(unique=true)
  private String email;

  private String Country;
  private String Password;
  private String Terms;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return Username;
  }

  public void setUsername(String Username) {
    this.Username = Username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry(){
    return Country;
  }

  public void setCountry(String Country){
    this.Country = Country;
  }

  public String getPassword(){
    return Password;
  }

  public void setPassword(String Password){
    this.Password = Password;
  }

  public String getTerms(){
    return Terms;
  }

  public void setTerms(String Terms){
    this.Terms = Terms;
  }

}