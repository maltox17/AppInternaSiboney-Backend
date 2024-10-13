package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para la creación de empleados.
 * Incluye los datos necesarios para registrar un empleado, incluyendo la clave.
 */
@Data
public class EmpleadoCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    private int telefono;
    private int horasContrato;
    private Rol rol;
}
