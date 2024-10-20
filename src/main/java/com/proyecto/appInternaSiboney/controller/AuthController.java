package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.JwtResponseDTO;
import com.proyecto.appInternaSiboney.dto.LoginDTO;
import com.proyecto.appInternaSiboney.security.JwtUtils;
import com.proyecto.appInternaSiboney.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getNombre(), loginRequest.getPassword())
        );
    
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
    
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    
        return ResponseEntity.ok(new JwtResponseDTO(
                jwt,
                jwt, userDetails.getId(),        // Usando getId()
                userDetails.getUsername(),  // Usando getUsername() 
                userDetails.getEmail(),     // Usando getEmail()
                userDetails.getAuthorities().toString()  // Roles o Authorities
        ));
    }
    
}
