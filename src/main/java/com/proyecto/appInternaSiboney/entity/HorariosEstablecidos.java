package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase HorariosEstablecidos representa los horarios fijos o predefinidos
 * que los empleados tienen de manera constante cada semana.
 * Estos horarios se aplican a empleados que trabajan con una programación regular.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorariosEstablecidos {

    /**
     * Identificador único del horario establecido (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha de inicio de la semana en la que se aplica el horario.
     * Esto permite identificar la semana específica en que el horario comienza a aplicarse.
     */
    private LocalDate semanaInicio;

    /**
     * Hora de entrada predefinida para los empleados con horario fijo.
     */
    private LocalTime horaEntrada;

    /**
     * Hora de salida predefinida para los empleados con horario fijo.
     */
    private LocalTime horaSalida;

    /**
     * Tipo de turno: Indica si el turno es continuo o partido.
     * Si es turno partido, el empleado tendrá otro bloque de horas para trabajar.
     */
    @Enumerated(EnumType.STRING)
    private Turno turno;

    /**
     * Empleado al que se le asigna este horario fijo.
     * Relación One-to-One con la entidad Empleado.
     */
    @OneToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    /**
     * Centro de trabajo en el que se aplica este horario establecido.
     * Relación Many-to-One con la entidad CentroTrabajo.
     */
    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroTrabajo centroTrabajo;
}


