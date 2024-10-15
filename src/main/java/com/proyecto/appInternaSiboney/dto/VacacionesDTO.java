package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.EstadoVacaciones;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO para transferir los datos de Vacaciones entre el controlador y el servicio.
 */
@Data
public class VacacionesDTO {

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoVacaciones estado;  // Enum que define los estados de la solicitud de vacaciones
    private Long empleadoId;  // Relación con el empleado
}
