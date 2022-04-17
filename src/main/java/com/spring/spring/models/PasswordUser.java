package com.spring.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswordUser {
    @NotEmpty(message="Agregue su contraseña")
    private String oldPassword;

    @NotEmpty(message="Agregue su nueva contraseña")
    @Size(min=8, max=128, message="Su contraseña debe ser mayor a 8 caracteres")
    private String password;

    public PasswordUser() {}

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
