package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.VacacionesDTO;
import com.proyecto.appInternaSiboney.dto.VacacionesNombreDTO;
import com.proyecto.appInternaSiboney.entity.Vacaciones;
import com.proyecto.appInternaSiboney.dto.VacacionesCreateDTO;

import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para gestionar vacaciones.
 */
public interface VacacionesService {

    VacacionesDTO crearVacaciones(VacacionesCreateDTO vacacionesCreateDTO);

    VacacionesDTO obtenerVacacionesPorId(Long id);

    List<VacacionesDTO> listarVacaciones();

    VacacionesDTO actualizarVacaciones(Long id, VacacionesCreateDTO vacacionesCreateDTO);

    void eliminarVacaciones(Long id);

    List<VacacionesDTO> listarVacacionesPorEmpleado(Long empleadoId);

    List<VacacionesDTO> obtenerVacacionesPorAno(Integer year);

    List<VacacionesNombreDTO> obtenerVacacionesconNombre();
}

