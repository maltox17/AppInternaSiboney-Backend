package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.VacacionesDTO;

import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para gestionar vacaciones.
 */
public interface VacacionesService {

    VacacionesDTO crearVacaciones(VacacionesDTO vacacionesDTO);

    VacacionesDTO obtenerVacacionesPorId(Long id);

    List<VacacionesDTO> listarVacaciones();

    VacacionesDTO actualizarVacaciones(Long id, VacacionesDTO vacacionesDTO);

    void eliminarVacaciones(Long id);

    List<VacacionesDTO> listarVacacionesPorEmpleado(Long empleadoId);

    List<VacacionesDTO> obtenerVacacionesPorAno(Integer year);
}

