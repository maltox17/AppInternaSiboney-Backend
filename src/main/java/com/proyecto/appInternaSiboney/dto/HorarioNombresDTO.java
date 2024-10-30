package com.proyecto.appInternaSiboney.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.proyecto.appInternaSiboney.entity.Turno;

import lombok.Data;

@Data
public class HorarioNombresDTO {

    private Long id;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Turno turno;
    private Long empleadoId;
    private String empleadoNombre; 
    private Long centroTrabajoId;
    private String centroNombre; 
}
