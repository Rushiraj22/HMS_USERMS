package com.hms.user.user.api;

import com.hms.user.user.Jwt.JwtUtil;
import com.hms.user.user.dto.LoginDTO;
import com.hms.user.user.dto.ResponseDTO;
import com.hms.user.user.dto.UserDTO;
import com.hms.user.user.exception.HmsException;
import com.hms.user.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserAPI {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws HmsException {
        System.out.printf("Registering user: %s%n", userDTO.getEmail());
        userService.registerUser(userDTO);
        return new ResponseEntity<>(new ResponseDTO("Account created"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> postMethodName(@RequestBody LoginDTO loginDTO) throws HmsException {

       try {
           authenticationManager.authenticate(
                   new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                           loginDTO.getEmail(), loginDTO.getPassword()
                   )
           );
       }
       catch (AuthenticationException e) {
           throw new HmsException("Invalid credentials");
       }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        String jwtToken = jwtUtil.generateToken(userDetails);
        System.out.printf("Generated JWT Token for user %s: %s%n", loginDTO.getEmail(), jwtToken);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getUser() {
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

}
