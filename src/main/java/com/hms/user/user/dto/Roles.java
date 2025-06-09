package com.hms.user.user.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN,
    DOCTOR,
    PATIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
