package com.hms.user.user.api;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws HmsException {
        System.out.printf("Registering user: %s%n", userDTO.getEmail());
        userService.registerUser(userDTO);
        return new ResponseEntity<>(new ResponseDTO("Account created"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> postMethodName(@RequestBody UserDTO userDTO) throws HmsException {

        return new ResponseEntity<>(userService.loginUser(userDTO), HttpStatus.OK);
    }

}
