package com.hms.user.user.service;

import com.hms.user.user.dto.UserDTO;
import com.hms.user.user.entity.User;
import com.hms.user.user.exception.HmsException;
import com.hms.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) throws HmsException {
        System.out.println("registerUser: " + userDTO.getEmail());
        Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());

        if (opt.isPresent()) {
            System.out.println("User already exists with email: " + userDTO.getEmail());
            throw new HmsException("User Already Exists");
        }
        System.out.println("User does not exist, proceeding with registration: " + userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        System.out.println("Encoded password for user: " + userDTO.getEmail());
        userRepository.save(userDTO.toEntity());
        System.out.println("User registered successfully: " + userDTO.getEmail());
    }

    @Override
    public UserDTO getUserById(Long id) throws HmsException {
        return userRepository.findById(id)
                .orElseThrow(() -> new HmsException("User with id not found")).toDTO();
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HmsException {

      User user= userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new HmsException("User with email " + userDTO.getEmail() + " not found"));

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new HmsException("Invalid password for user with email ");
        }
       user.setPassword(null); // Clear password before returning
        return user.toDTO();
    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }

    @Override
    public UserDTO getUser(String email) throws HmsException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new HmsException("User with email " + email + " not found")).toDTO();
    }
}
