package com.proyecto.appInternaSiboney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoCentroCreateDTO {
    private Long empleadoId;
    private Long centroTrabajoId;
    private boolean esEncargado;
}

