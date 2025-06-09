package com.hms.user.user.Jwt;

import com.hms.user.user.dto.Roles;
import com.hms.user.user.dto.UserDTO;
import com.hms.user.user.exception.HmsException;
import com.hms.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Logic to fetch user from the database and return a CustomUserDetails object
        // For example:
        try {
            UserDTO dto = userService.getUser(email);
            Roles roleEnum = Roles.valueOf(dto.getRoles().toUpperCase());
            return new CustomUserDetails(
                    dto.getId(),
                    dto.getEmail(),     // username
                    dto.getPassword(),
                    roleEnum,
                    dto.getName(),
                    dto.getEmail()      // email
            );
//                public CustomUserDetails(Long id, String username, String password, Roles role, String name, String email)
            } catch (HmsException e) {
                throw new RuntimeException(e);
            }

        }
        // User user = userRepository.findByUsername(username);
        // return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.getName(), user.getEmail());
         // Placeholder, implement actual logic here


}
