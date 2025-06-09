package com.hms.user.user.dto;

import lombok.Data;

@Data

public class LoginDTO {
    private String email;
    private String password;

    // No-args constructor for JPA
    public LoginDTO() {
    }

    // All args constructor for JPA
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // Getters and Setters
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
