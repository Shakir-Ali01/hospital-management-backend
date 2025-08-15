package com.hms.user.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hms.user.dto.UserDTO;
import com.hms.user.exceptions.HmsExceptions;
import com.hms.user.service.UserService;
@Service
public class MyUserDetailsService implements UserDetailsService {

    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Logic to load user by username from the database or any other source
        // For example, you can use a UserRepository to fetch the user details
        // If user not found, throw new UsernameNotFoundException("User not found with username: " + username);
        try{
             UserDTO userDTO = userService.getUserByEmail(username);  
             return new CustomerUserDetails(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole(),
                userDTO.getName(),
                null);
        }catch(HmsExceptions e){
            e.printStackTrace();
            
        }   
        return null; // Replace with actual UserDetails object
    }
   
}
