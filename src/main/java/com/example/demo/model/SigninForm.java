package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;

public class SigninForm {

	
	@NotBlank(message = "Username  must not be Blank")
	private String Username;

	@NotBlank(message = "Password  must not be Blank")
    private String Password;

	public String getUsername() {
		return this.Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

    public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public String toString() {
		return "Person(Name: " + this.Username;

	}

}