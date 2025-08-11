package com.hms.user.service;

import com.hms.user.dto.UserDTO;
import com.hms.user.exceptions.HmsExceptions;

public interface UserService {
    public void createUser(UserDTO userDTO) throws HmsExceptions;
    public UserDTO loginUser(UserDTO userDTO) throws HmsExceptions;
    public UserDTO getUserById(Long id) throws HmsExceptions;
    public void updateUser(UserDTO userDTO);
}
