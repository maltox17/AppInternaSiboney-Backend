package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalTime;

/**
 * La clase HorariosEstablecidos representa los horarios fijos o predefinidos
 * que los empleados tienen de manera constante cada semana.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorariosEstablecidos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha de inicio de la semana en la que se aplica el horario.
     * Esto permite identificar la semana específica en que el horario comienza a aplicarse.
     */
    private DiaSemana diaSemana;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroTrabajo centroTrabajo;
}


