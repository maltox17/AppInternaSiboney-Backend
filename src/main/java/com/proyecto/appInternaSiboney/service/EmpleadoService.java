package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.EmpleadoDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoCreateDTO;
import com.proyecto.appInternaSiboney.entity.Rol;

import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoCreateDTO empleadoCreateDTO);
    EmpleadoDTO obtenerEmpleadoPorId(Long id);
    List<EmpleadoDTO> listarEmpleados();
    EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);
    boolean eliminarEmpleado(Long id);
    List<EmpleadoDTO> buscarEmpleadosPorRol(Rol rol);
}