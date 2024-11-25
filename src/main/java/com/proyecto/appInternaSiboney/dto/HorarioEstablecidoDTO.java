package com.proyecto.appInternaSiboney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioEstablecidoDTO {

    private Long id;
    
    private String diaSemana;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEntrada;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalida;

    private String turno;

    private Long empleadoId;

    private Long centroTrabajoId;

    private String empleadoNombre;

    private String centroNombre;
}
