package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


/**
 * Representa la relación N:M entre Empleado y CentroTrabajo.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados_centro")
@IdClass(EmpleadoCentroPK.class)
public class EmpleadoCentro {


    @Id
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Id
    @ManyToOne
    @JoinColumn(name = "centro_id", nullable = false)
    private CentroTrabajo centroTrabajo;

    private boolean esEncargado;
}

