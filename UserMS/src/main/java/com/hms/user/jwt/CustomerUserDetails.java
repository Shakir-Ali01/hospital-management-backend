package com.hms.user.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hms.user.dto.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserDetails implements UserDetails {
    private Long id;
    private String username;//this will be our email
     // this is our entity field
    private String email;
    private String password;  
    private Roles role;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
 
}
