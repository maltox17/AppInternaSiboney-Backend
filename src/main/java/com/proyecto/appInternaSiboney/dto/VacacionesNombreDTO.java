package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.EstadoVacaciones;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacacionesNombreDTO {
    
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoVacaciones estado;  
    private Long empleadoId;
    private String empleadoNombre;

}
