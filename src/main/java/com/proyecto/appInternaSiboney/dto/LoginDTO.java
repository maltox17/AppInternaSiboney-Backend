package com.proyecto.appInternaSiboney.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de autenticación (login).
 * Contiene el nombre de usuario y la contraseña.
 */
@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "El email es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
