package com.proyecto.appInternaSiboney.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.appInternaSiboney.entity.Turno;

import lombok.Data;

@Data
public class HorarioNombresDTO {

    private Long id;
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEntrada;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalida;
    
    private Turno turno;
    private Long empleadoId;
    private String empleadoNombre; 
    private Long centroTrabajoId;
    private String centroNombre; 
}
