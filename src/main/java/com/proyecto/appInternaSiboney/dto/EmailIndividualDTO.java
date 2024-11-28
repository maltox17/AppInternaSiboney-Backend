package com.proyecto.appInternaSiboney.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailIndividualDTO {

    @Email(message = "El formato del email es inválido")
    private String destinatarioEmail;

    private String Asunto;
    
    private String mensaje;

    
}
