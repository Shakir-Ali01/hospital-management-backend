package com.hms.user.dto;

import com.hms.user.dto.Roles;
import com.hms.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message="Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message="Password should not be empty")
    private String password;
    @Pattern(regexp = "PATIENT|DOCTOR|ADMIN", message = "Role must be PATIENT, DOCTOR, or ADMIN")
    private Roles role;

}
