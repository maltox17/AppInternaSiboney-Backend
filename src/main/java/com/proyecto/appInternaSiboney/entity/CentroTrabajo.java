package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entidad que representa un centro de trabajo.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentroTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;
}
