package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    /**
     * Dirección de correo electrónico del empleado.
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email es inválido")
    @Size(max = 150, message = "El email no puede tener más de 150 caracteres")
    private String email;

    private String username;

    /**
     * Contraseña cifrada del empleado para acceder al sistema.
     */
    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 8, message = "La clave debe tener al menos 8 caracteres")
    private String clave;

    /**
     * Número de teléfono del empleado.
     */
    @NotNull(message = "El número de teléfono es obligatorio")
    private Integer telefono;

    /**
     * Número de horas de contrato que el empleado debe cumplir semanalmente.
     */
    @NotNull(message = "Las horas de contrato son obligatorias")
    private Integer horasContrato;

    /**
     * Rol del empleado en el sistema (empleado, encargado, jefe).
     */
    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    /**
     * Lista de solicitudes de vacaciones del empleado.
     */
    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY)
    private List<Vacaciones> vacaciones;

    /**
     * Lista de horarios asignados al empleado.
     */
    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY)
    private List<Horario> horarios;

    /**
     * Horario establecido de manera fija para el empleado.
     */
    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY)
    private List<HorariosEstablecidos> horariosEstablecidos;

    @PrePersist
    public void prePersist() {
        // Asignar username igual al email antes de persistir en la BD
        this.username = this.email;
    }


}
