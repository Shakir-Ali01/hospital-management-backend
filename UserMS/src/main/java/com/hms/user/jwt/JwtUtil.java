package com.hms.user.jwt;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private static final Long JWT_EXPIRATION_TIME = 1000 * 60 * 60L; // 1 hour
    private static final String JWT_SECRET_KEY="37a2545de4851cbf6456f58f0e921cee546d3c3eb8ea29a14cc99fc8c54b82508e028b1d53bcd27849a7e4338eedd914252531a404d2bdd0bf384ef1fcdcd710";
    public String generateToken(UserDetails userDetails) {
        // Logic to generate JWT token using the username
        // This is a placeholder; actual implementation will depend on your JWT library
        Map<String, Object> claims = new HashMap<>();
        CustomerUserDetails customerUserDetails = (CustomerUserDetails) userDetails;
        claims.put("id", customerUserDetails.getId());
        claims.put("email", customerUserDetails.getEmail());        
        claims.put("role", customerUserDetails.getRole());
        claims.put("name", customerUserDetails.getName());
        claims.put("profileId", customerUserDetails.getProfileId());
        return doGenerateToken(claims, customerUserDetails.getUsername());
    }
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        // Logic to generate JWT token using the User entity
        // This is a placeholder; actual implementation will depend on your JWT library
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY).compact();

    }
}
