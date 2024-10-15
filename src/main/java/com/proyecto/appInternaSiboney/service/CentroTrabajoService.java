package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import java.util.List;

/**
 * Servicio que define las operaciones relacionadas con CentroTrabajo.
 */
public interface CentroTrabajoService {

    CentroTrabajo crearCentroTrabajo(CentroTrabajo centroTrabajo);
    CentroTrabajo obtenerCentroTrabajoPorId(Long id);
    List<CentroTrabajo> listarCentrosTrabajo();
    CentroTrabajo actualizarCentroTrabajo(Long id, CentroTrabajo centroTrabajo);
    boolean eliminarCentroTrabajo(Long id);
}
