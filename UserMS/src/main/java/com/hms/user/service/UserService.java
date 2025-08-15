package com.hms.user.service;

import com.hms.user.dto.LoginDTO;
import com.hms.user.dto.UserDTO;
import com.hms.user.exceptions.HmsExceptions;

import jakarta.validation.Valid;

public interface UserService {
    public void createUser(UserDTO userDTO) throws HmsExceptions;
    // public String loginUser(LoginDTO loginDTO) throws HmsExceptions;
    public UserDTO getUserById(Long id) throws HmsExceptions;
    public void updateUser(UserDTO userDTO);
    public UserDTO getUserByEmail(String email) throws HmsExceptions;

}
