package com.proyecto.appInternaSiboney.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre del centro es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección del centro es obligatoria")
    @Size(max = 150, message = "La dirección no puede tener más de 150 caracteres")
    private String direccion;
}
