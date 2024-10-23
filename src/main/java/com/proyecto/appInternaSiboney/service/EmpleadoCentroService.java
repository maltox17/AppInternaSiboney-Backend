package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.EmpleadoCentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoCentroDTO;

import java.util.List;

public interface EmpleadoCentroService {
    EmpleadoCentroDTO crearEmpleadoCentro(EmpleadoCentroCreateDTO empleadoCentroCreateDTO);
    EmpleadoCentroDTO obtenerEmpleadoCentro(Long empleadoId, Long centroId);
    List<EmpleadoCentroDTO> listarEmpleadosCentro();
    List<EmpleadoCentroDTO> obtenerCentrosPorEmpleado(Long empleadoId);
    List<EmpleadoCentroDTO> obtenerEmpleadosPorCentro(Long centroId);
    void eliminarEmpleadoCentro(Long empleadoId, Long centroId);
}
