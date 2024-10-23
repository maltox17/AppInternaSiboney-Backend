package com.proyecto.appInternaSiboney.excepcion;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

}
