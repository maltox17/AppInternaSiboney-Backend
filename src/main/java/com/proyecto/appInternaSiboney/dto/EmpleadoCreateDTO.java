package com.proyecto.appInternaSiboney.dto;

import com.proyecto.appInternaSiboney.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;;

/**
 * DTO para la creación de empleados.
 * Incluye los datos necesarios para registrar un empleado, incluyendo la clave.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoCreateDTO {

    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+(?: [a-zA-Z]+)?$", message = "El nombre solo debe contener letras y espacios")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotNull
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email es inválido")
    @Size(max = 150, message = "El email no puede tener más de 150 caracteres")
    private String email;

    @NotNull
    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @NotNull(message = "El número de teléfono es obligatorio")
    @Min(value = 100000000, message = "El número de teléfono debe tener 9 dígitos")
    @Max(value = 999999999, message = "El número de teléfono debe tener 9 dígitos")
    private Integer telefono;

    @NotNull(message = "Las horas de contrato son obligatorias")
    @Min(value = 1, message = "Las horas de contrato deben ser al menos 1")
    @Max(value = 40, message = "Las horas de contrato no pueden superar las 40 horas")
    private Integer horasContrato;

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;

}
