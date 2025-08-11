package com.hms.user.service;
import com.hms.user.dto.UserDTO;
import com.hms.user.entity.User;
import com.hms.user.exceptions.HmsExceptions;
import com.hms.user.repository.UserRepository;
import com.hms.user.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    //ingectiong the repository
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // now injected via constructor
    private final UserMapper userMapper;//now injected via constructor
    @Override
    public void createUser(UserDTO userDTO) throws HmsExceptions {

        Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());
        if (opt.isPresent()) {
            throw new HmsExceptions("USER_ALREADY_EXISTS");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userMapper.toEntity(userDTO));
    }
    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HmsExceptions {
        User user=userRepository.findByEmail(userDTO.getEmail()).orElseThrow(()->new HmsExceptions("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(userDTO.getPassword(),user.getPassword())){
            throw new HmsExceptions("INVALID_CREDENTIALS");
        }
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws HmsExceptions {
        return userMapper.toDTO(
                userRepository.findById(id)
                        .orElseThrow(() -> new HmsExceptions("USER_NOT_FOUND"))
        );
    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }
}
