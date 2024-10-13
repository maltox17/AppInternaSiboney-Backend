package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.Rol;
import lombok.Data;

/**
 * DTO para transferir los datos de Empleado entre el controlador y el servicio.
 * Excluye datos sensibles como la clave.
 */
@Data
public class EmpleadoDTO {

    private Long id;
    private String nombre;
    private String email;
    private int telefono;
    private int horasContrato;
    private Rol rol;  
    
}
