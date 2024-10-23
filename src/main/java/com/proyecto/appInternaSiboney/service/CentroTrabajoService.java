package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.CentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.CentroDTO;
import java.util.List;

/**
 * Servicio que define las operaciones relacionadas con CentroTrabajo.
 */
public interface CentroTrabajoService {
    CentroDTO crearCentroTrabajo(CentroCreateDTO centroCreateDTO);
    CentroDTO obtenerCentroTrabajoPorId(Long id);
    List<CentroDTO> listarCentrosTrabajo();
    CentroDTO actualizarCentroTrabajo(Long id, CentroCreateDTO centroCreateDTO);
    void eliminarCentroTrabajo(Long id);
}

