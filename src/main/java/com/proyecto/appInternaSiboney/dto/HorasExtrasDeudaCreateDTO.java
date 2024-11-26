package com.proyecto.appInternaSiboney.dto;

import lombok.Data;

@Data
public class HorasExtrasDeudaCreateDTO {
    private int horasExtras;
    private int horasDeuda;
    private long empleadoId;
}
