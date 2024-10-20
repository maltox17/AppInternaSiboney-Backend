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
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
    
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
    
        // Obtener detalles del usuario autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    
        // Devolver el token JWT y los detalles del usuario (email y rol)

        JwtResponseDTO respuesta = new JwtResponseDTO(jwt, "Bearer", userDetails.getEmail(), userDetails.getRol());
        return ResponseEntity.ok().body(respuesta);
    }
    
}
