package com.hms.user.utility;

import com.hms.user.dto.UserDTO;
import com.hms.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getProfileId()
        );
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRole(),
                dto.getProfileId()
        );
    }
}
