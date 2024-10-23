package com.proyecto.appInternaSiboney.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class CentroCreateDTO {

    @NotBlank(message = "El nombre del centro es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección del centro es obligatoria")
    @Size(max = 200, message = "La dirección no puede tener más de 200 caracteres")
    private String direccion;
}

