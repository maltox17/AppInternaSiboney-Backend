package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * La clase HorasExtrasDeuda representa el registro de horas extras realizadas o 
 * las horas que un empleado debe compensar.
 */
@Entity
@Table(name = "horas_extras_deuda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorasExtrasDeuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int horasExtras;


    private int horasDeuda;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
