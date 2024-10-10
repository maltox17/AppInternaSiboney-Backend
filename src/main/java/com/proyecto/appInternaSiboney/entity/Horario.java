package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase Horario representa los turnos de trabajo asignados a los empleados.
 * Cada horario está asociado a una fecha específica, horas de entrada y salida,
 * así como a un empleado y un centro de trabajo.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    /**
     * Identificador único del horario (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha en la que aplica este horario.
     */
    private LocalDate fecha;

    /**
     * Hora de entrada del turno asignado.
     */
    private LocalTime horaEntrada;

    /**
     * Hora de salida del turno asignado.
     */
    private LocalTime horaSalida;

    /**
     * Empleado al que está asignado este horario.
     * Relación Many-to-One con la entidad Empleado.
     */
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    /**
     * Centro de trabajo en el que aplica este horario.
     * Relación Many-to-One con la entidad CentroTrabajo.
     */
    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroTrabajo centroTrabajo;
}

