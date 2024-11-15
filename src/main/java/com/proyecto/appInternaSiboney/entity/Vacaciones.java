package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * La clase Vacaciones representa las solicitudes de vacaciones de los empleados en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vacaciones {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoVacaciones estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}


