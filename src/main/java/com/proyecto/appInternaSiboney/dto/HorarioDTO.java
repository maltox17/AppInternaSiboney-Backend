package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.Turno;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class HorarioDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Turno turno; 
    private Long empleadoId;
    private Long centroTrabajoId;
}
