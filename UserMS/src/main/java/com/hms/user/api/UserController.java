package com.hms.user.api;

import com.hms.user.dto.LoginDTO;
import com.hms.user.dto.ResponseDTO;
import com.hms.user.dto.UserDTO;
import com.hms.user.exceptions.HmsExceptions;
import com.hms.user.jwt.JwtUtil;
import com.hms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid  UserDTO userDTO) throws HmsExceptions {
            userService.createUser(userDTO);
            return new ResponseEntity<>(new ResponseDTO("User Created Successfully"), HttpStatus.CREATED);
    }


  @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws HmsExceptions {
       try{
          authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
       }catch(AuthenticationException e){
            throw new HmsExceptions("INVALID_CREDENTIALS");
       }
       final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        if (userDetails == null) {
            throw new HmsExceptions("USER_NOT_FOUND");
        }
        String token = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Test",HttpStatus.OK);
    }
}
