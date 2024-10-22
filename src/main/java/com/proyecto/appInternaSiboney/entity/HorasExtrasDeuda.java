package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * La clase HorasExtrasDeuda representa el registro de horas extras realizadas o 
 * las horas que un empleado debe compensar. Esta entidad se asocia a un empleado
 * y permite llevar un control de las horas a favor o en deuda.
 */
@Entity
@Table(name = "horas_extras_deuda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorasExtrasDeuda {

    /**
     * Identificador único del registro de horas extras o deuda (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Número de horas extras realizadas por el empleado.
     * Si el empleado ha trabajado más horas de las acordadas en su contrato,
     * este campo almacena dichas horas.
     */
    private int horasExtras;

    /**
     * Número de horas que el empleado debe.
     * Si el empleado ha trabajado menos horas de las estipuladas en su contrato,
     * este campo almacena las horas que debe compensar.
     */
    private int horasDeuda;

    /**
     * Empleado asociado a este registro de horas extras o deuda.
     * Relación Many-to-One con la entidad Empleado.
     */
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
