package com.supplynext.company_api.security;

import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class will contain utility logics like generateJwtToken, DecryptJwtToken,
 * ValidateJwtToken
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt-token-expiration-time}")
    Long expirationTime;
    @Value("${jwt-secret-password}")
    String secretPassword;
    @Autowired
    UserService userService;

    public String generateJwtToken(String email,
                                   List<String> roleNames){
        // As we want to generate JwtToken we need to think what user information we want to encrypt
        // In my Jwt Token I want to encrypt useremail and user roles
        // Whatever the information which i want to encrypt int jwt token
        // We call thems claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("roles", roleNames);
        String jwtToken = Jwts
                .builder()
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretPassword)
                .setClaims(claims)
                .compact();
        return jwtToken;
    }

    public Claims decryptToken(String jwtToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretPassword)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }

    public boolean isTokenValid(String jwtToken){
        Claims claims = this.decryptToken(jwtToken);
        String email = claims.get("email", String.class);
        List<String> roleNames = claims.get("roles", List.class);
        User user = userService.getUserByEmail(email);
        if(user == null){
            return false;
        }
        List<Role> roles = user.getRoles();
        for(int i = 0; i < roleNames.size(); i++){
            String roleName = roleNames.get(i);
            boolean flag = false;
            for(int j = 0; j < roles.size(); j++){
                String dbRoleName = roles.get(i).getRoleName();
                if(roleName.equals(dbRoleName)){
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                log.warn("Invalid Token");
                return false;
            }
        }
        log.info("Token is valid");
        return true;

    }


}
