package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * La clase Empleado representa a un trabajador.
 * Un empleado puede tener diferentes roles dentro del sistema
 * cada empleado está asociado a un centro de trabajo
 * y puede tener multiples horarios y solicitudes de vacaciones.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String username;

    private String clave;

    private Integer telefono;

    private Integer horasContrato;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacaciones> vacaciones;
    
    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Horario> horarios;
    
    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorariosEstablecidos> horariosEstablecidos;

    @PrePersist
    public void prePersist() {
        // Asignar username igual al email antes de persistir en la BD
        this.username = this.email;
    }


}
