package com.hms.user.user.Jwt;

import com.hms.user.user.dto.Roles;
import com.hms.user.user.dto.UserDTO;
import com.hms.user.user.exception.HmsException;
import com.hms.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

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
              e.printStackTrace();
            }
         return null; // or throw an exception if user not found
    }


}
