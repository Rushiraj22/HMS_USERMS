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
}
