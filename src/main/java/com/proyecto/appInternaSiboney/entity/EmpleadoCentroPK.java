package com.proyecto.appInternaSiboney.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la entidad EmpleadoCentro.
 * La clave primaria está compuesta por los identificadores de empleado y centro de trabajo.
 * Esta clase es necesaria para mapear correctamente la relación N:M entre las entidades Empleado y CentroTrabajo.
 */
public class EmpleadoCentroPK implements Serializable {

    /**
     * Identificador del empleado asociado a la relación N:M.
     * Este campo es parte de la clave primaria compuesta.
     */
    private Long empleado;

    /**
     * Identificador del centro de trabajo asociado a la relación N:M.
     * Este campo es parte de la clave primaria compuesta.
     */
    private Long centroTrabajo;

    /**
     * Constructor por defecto.
     * Necesario para el correcto funcionamiento de JPA.
     */
    public EmpleadoCentroPK() {}

    /**
     * Constructor que recibe el identificador del empleado y del centro de trabajo.
     * 
     * @param empleado identificador del empleado
     * @param centroTrabajo identificador del centro de trabajo
     */
    public EmpleadoCentroPK(Long empleado, Long centroTrabajo) {
        this.empleado = empleado;
        this.centroTrabajo = centroTrabajo;
    }

    /**
     * Compara dos objetos para verificar si son iguales.
     * Sobrescribe el método equals para comparar ambos identificadores (empleado y centroTrabajo).
     *

     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoCentroPK that = (EmpleadoCentroPK) o;
        return Objects.equals(empleado, that.empleado) &&
                Objects.equals(centroTrabajo, that.centroTrabajo);
    }

    /**
     * Calcula el código hash de la clave primaria compuesta.
     * Sobrescribe el método hashCode para generar el código hash basado en los identificadores.
     * 
     * @return el código hash de la clave primaria compuesta
     */
    @Override
    public int hashCode() {
        return Objects.hash(empleado, centroTrabajo);
    }
}

