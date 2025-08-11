package com.hms.user.api;

import com.hms.user.dto.ResponseDTO;
import com.hms.user.dto.UserDTO;
import com.hms.user.exceptions.HmsExceptions;
import com.hms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid  UserDTO userDTO) throws HmsExceptions {
            userService.createUser(userDTO);
            return new ResponseEntity<>(new ResponseDTO("User Created Successfully"), HttpStatus.CREATED);
    }
    @GetMapping("/test-exception")
    public ResponseEntity<String> testException() throws HmsExceptions {
        throw new HmsExceptions("This is a test HmsException");
    }

  @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid UserDTO userDTO) throws HmsExceptions {
        return new ResponseEntity<>(userService.loginUser(userDTO), HttpStatus.OK);
    }
}
