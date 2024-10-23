package com.proyecto.appInternaSiboney.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoCentroCreateDTO {

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "El ID del centro de trabajo es obligatorio")
    private Long centroTrabajoId;

    @NotNull(message = "El campo 'esEncargado' es obligatorio")
    private Boolean esEncargado;

 
}

