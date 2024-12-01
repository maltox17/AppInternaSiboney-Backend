package com.proyecto.appInternaSiboney.dto;

import java.time.LocalDate;

import com.proyecto.appInternaSiboney.entity.EstadoVacaciones;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VacacionesCreateDTO {

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "El estado de las vacaciones es obligatorio")
    private EstadoVacaciones estado;  

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long empleadoId;  
}
