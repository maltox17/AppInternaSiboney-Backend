package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * La clase Vacaciones representa las solicitudes de vacaciones de los empleados en el sistema.
 * Cada solicitud de vacaciones tiene una fecha de inicio, una fecha de fin, un estado que indica
 * si ha sido aprobada, rechazada o está pendiente, y está asociada a un empleado.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vacaciones {

    /**
     * Identificador único de la solicitud de vacaciones (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha de inicio de las vacaciones.
     * Este campo indica el primer día de las vacaciones solicitadas por el empleado.
     */
    private LocalDate fechaInicio;

    /**
     * Fecha de fin de las vacaciones.
     * Este campo indica el último día de las vacaciones solicitadas por el empleado.
     */
    private LocalDate fechaFin;

    /**
     * Estado de la solicitud de vacaciones.
     * Puede ser "PENDIENTE", "APROBADA" o "RECHAZADA".
     * Se usa una enumeración para definir los estados posibles.
     */
    @Enumerated(EnumType.STRING)
    private EstadoVacaciones estado;

    /**
     * Empleado que realiza la solicitud de vacaciones.
     * Relación Many-to-One con la entidad Empleado, ya que un empleado puede realizar múltiples solicitudes.
     */
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}


