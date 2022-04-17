package com.spring.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
    // //// VARIABLES /////
    @NotEmpty(message="¡Agregue su email!")
    @Email(message="¡Agregue un email valido!")
    private String email;

    @NotEmpty(message="¡Agregue su contraseña!")
    @Size(min=8, max=128, message="¡Su contraseña debe ser mayor a 8 digitos!")
    private String password;

    // //// CONSTRUCTOR ////
    public LoginUser() {}

    // //// GETTERS AND SETTERS ////
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
