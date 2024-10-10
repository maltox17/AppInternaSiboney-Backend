package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


/**
 * La clase EmpleadoCentro representa la relación N:M entre Empleado y CentroTrabajo.
 * Un empleado puede estar asignado a varios centros de trabajo y un centro puede tener varios empleados.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados_centro")
@IdClass(EmpleadoCentroPK.class)
public class EmpleadoCentro {

    /**
     * Relación Many-to-One con la entidad Empleado.
     * Un empleado puede estar asignado a varios centros de trabajo.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    /**
     * Relación Many-to-One con la entidad CentroTrabajo.
     * Un centro de trabajo puede tener varios empleados asignados.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "centro_id", nullable = false)
    private CentroTrabajo centroTrabajo;

    /**
     * Indica si el empleado es responsable (encargado) del centro.
     * Este campo puede ser utilizado para definir si el empleado tiene un rol de gestión en el centro.
     */
    private boolean esEncargado;
}

