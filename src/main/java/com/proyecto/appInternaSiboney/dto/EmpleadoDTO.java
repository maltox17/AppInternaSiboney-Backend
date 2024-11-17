package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private Long id;
    private String nombre;
    private String email;
    private int telefono;
    private int horasContrato;
    private Rol rol;  
    
}
