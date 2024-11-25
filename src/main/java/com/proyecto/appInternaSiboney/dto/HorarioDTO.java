package com.proyecto.appInternaSiboney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.appInternaSiboney.entity.Turno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDTO {
    private Long id;
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEntrada;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalida;
    
    private Turno turno; 
    private Long empleadoId;
    private Long centroTrabajoId;
    private String centroNombre;
}
