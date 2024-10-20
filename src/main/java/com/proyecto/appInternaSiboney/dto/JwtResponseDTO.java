package com.proyecto.appInternaSiboney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO para la respuesta de autenticación exitosa.
 * Incluye el token JWT y la información básica del usuario.
 */
@Data
@AllArgsConstructor
public class JwtResponseDTO {

    private String accessToken;
    private String tokenType;
    private String username;
    private String rol;
}

