package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.Turno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioCreateDTO {

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora de entrada es obligatoria")
    private LocalTime horaEntrada;

    @NotNull(message = "La hora de salida es obligatoria")
    private LocalTime horaSalida;

    @NotNull(message = "El turno es obligatorio")
    @NotBlank(message = "El turno no puede estar vacío")
    private Turno turno;

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "El ID del centro de trabajo es obligatorio")
    private Long centroTrabajoId;
}
