package com.proyecto.appInternaSiboney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioEstablecidoCreateDTO {

    @NotNull(message = "El día de la semana es obligatorio")
    @NotBlank(message = "El día de la semana no puede estar vacío")
    @Pattern(regexp = "^(LUNES|MARTES|MIERCOLES|JUEVES|VIERNES|SABADO)$", message = "El día de la semana debe ser válido (LUNES, MARTES, etc.)")
    private String diaSemana;

    @NotNull(message = "La hora de entrada es obligatoria")
    private LocalTime horaEntrada;

    @NotNull(message = "La hora de salida es obligatoria")
    private LocalTime horaSalida;

    @NotNull(message = "El turno es obligatorio")
    @NotBlank(message = "El turno no puede estar vacío")
    private String turno;

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "El ID del centro de trabajo es obligatorio")
    private Long centroTrabajoId;
}
