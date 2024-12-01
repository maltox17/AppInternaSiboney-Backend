package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.EstadoVacaciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacacionesDTO {

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoVacaciones estado;  
    private Long empleadoId;  
}
