package com.hms.user.user.service;

import com.hms.user.user.dto.UserDTO;
import com.hms.user.user.exception.HmsException;

public interface UserService {
    public void registerUser(UserDTO userDTO) throws HmsException;

    public UserDTO getUserById(Long id) throws HmsException;

    public UserDTO loginUser(UserDTO userDTO) throws HmsException;

    public void updateUser(UserDTO userDTO) throws HmsException;

    public UserDTO getUser(String email) throws HmsException;

}