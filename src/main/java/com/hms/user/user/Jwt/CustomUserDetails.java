package com.hms.user.user.Jwt;

import com.hms.user.user.dto.Roles;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Roles role;
    private String name;
    private String email;

   private List<Roles> authorities;

//No Arg constructor for JPA
    public CustomUserDetails() {
    }

    // All args constructor for JPA
    public CustomUserDetails(Long id, String username, String password, Roles role, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.authorities = Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
