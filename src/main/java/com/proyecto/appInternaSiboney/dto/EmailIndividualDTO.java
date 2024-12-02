package com.proyecto.appInternaSiboney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailIndividualDTO {

    private Long empleadoId;

    private String Asunto;
    
    private String mensaje;

    
}
