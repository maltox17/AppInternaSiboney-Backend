package com.proyecto.appInternaSiboney.dto;

import lombok.Data;

@Data
public class HorasExtrasDeudaDTO {
    private Long id;
    private int horasExtras;
    private int horasDeuda;
    private Long empleadoId;
    private String empleadoNombre;
}

