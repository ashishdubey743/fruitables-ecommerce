package com.example.demo.model.forms;

import jakarta.validation.constraints.NotBlank;

public class SigninForm {

	
	@NotBlank(message = "Email  must not be Blank")
	private String email;

	@NotBlank(message = "Password  must not be Blank")
    private String Password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public String toString() {
		return "Person(Name: " + this.email;

	}

}