package com.proyecto.appInternaSiboney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoCentroDTO {
    private Long empleadoId;
    private Long centroTrabajoId;
    private boolean esEncargado;
    private String empleadoNombre;
    private String centroNombre;
}
