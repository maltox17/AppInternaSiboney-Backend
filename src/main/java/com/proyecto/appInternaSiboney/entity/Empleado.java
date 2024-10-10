package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * La clase Empleado representa a un trabajador en el sistema de gestión de Siboney.
 * Un empleado puede tener diferentes roles dentro del sistema, como empleado regular,
 * encargado o jefe. Además, cada empleado está asociado a un centro de trabajo
 * y puede tener múltiples horarios y solicitudes de vacaciones.
 */
@Entity
@Data // Lombok genera getters, setters, equals, hashCode y toString
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los atributos
public class Empleado {
    
    /**
     * Identificador único del empleado (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del empleado.
     */
    private String nombre;

    /**
     * Dirección de correo electrónico del empleado.
     */
    private String email;

    /**
     * Contraseña cifrada del empleado para acceder al sistema.
     */
    private String clave;

    /**
     * Número de teléfono del empleado.
     */
    private int telefono;

    /**
     * Número de horas de contrato que el empleado debe cumplir semanalmente.
     */
    private int horasContrato;

    /**
     * Rol del empleado en el sistema (empleado, encargado, jefe).
     * Esta propiedad utiliza una enumeración para definir los roles posibles.
     */
    @Enumerated(EnumType.STRING)
    private Rol rol; // Enum para definir los roles de empleado, encargado, jefe

    /**
     * Lista de solicitudes de vacaciones del empleado.
     * Relación One-to-Many con la entidad Vacaciones.
     * Un empleado puede tener varias solicitudes de vacaciones.
     */
    @OneToMany(mappedBy = "empleado")
    private List<Vacaciones> vacaciones;

    /**
     * Centro de trabajo al que está asignado el empleado.
     * Relación Many-to-One con la entidad CentroTrabajo.
     * Un empleado pertenece a un único centro de trabajo.
     */
    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroTrabajo centroTrabajo;

    /**
     * Lista de horarios asignados al empleado.
     * Relación One-to-Many con la entidad Horario.
     * Un empleado puede tener múltiples horarios (turnos de trabajo).
     */
    @OneToMany(mappedBy = "empleado")
    private List<Horario> horarios;

    /**
     * Horario establecido de manera fija para el empleado.
     * Relación One-to-One con la entidad HorariosEstablecidos.
     * Este atributo se usa para empleados que tienen un horario predefinido.
     */
    @OneToOne(mappedBy = "empleado")
    private HorariosEstablecidos horarioEstablecido;

    /**
     * Encargado del empleado en caso de que esté subordinado a otro empleado.
     * Relación Many-to-One con la entidad Empleado, reflejando una jerarquía.
     */
    @ManyToOne
    @JoinColumn(name = "encargado_id")
    private Empleado encargado;

    /**
     * Lista de empleados subordinados a este empleado (si es encargado).
     * Relación One-to-Many con la entidad Empleado, reflejando una jerarquía.
     * Solo se aplica si el empleado tiene el rol de encargado o jefe.
     */
    @OneToMany(mappedBy = "encargado")
    private List<Empleado> subordinados;
}
