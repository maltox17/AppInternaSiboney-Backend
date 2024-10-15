package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.service.CentroTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para la entidad CentroTrabajo.
 */
@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {

    @Autowired
    CentroTrabajoRepository centroTrabajoRepository;
    
    public CentroTrabajo crearCentroTrabajo(CentroTrabajo centroTrabajo) {
        return centroTrabajoRepository.save(centroTrabajo);
    }

    
    public CentroTrabajo obtenerCentroTrabajoPorId(Long id) {
        return centroTrabajoRepository.findById(id).orElse(null);
    }

    
    public List<CentroTrabajo> listarCentrosTrabajo() {
        return centroTrabajoRepository.findAll();
    }

    
    public CentroTrabajo actualizarCentroTrabajo(Long id, CentroTrabajo centroTrabajo) {
        // Buscar el centro de trabajo por su ID
        CentroTrabajo centroExistente = centroTrabajoRepository.findById(id).orElse(null);
    
        // Verificar si el centro existe
        if (centroExistente != null) {
            centroExistente.setNombre(centroTrabajo.getNombre());
            centroExistente.setDireccion(centroTrabajo.getDireccion());
            return centroTrabajoRepository.save(centroExistente); // Guardar los cambios
        } else {
            return null; // Si no se encuentra el centro, devolver null
        }
    }
    

    public boolean eliminarCentroTrabajo(Long id) {

        if (!centroTrabajoRepository.existsById(id)) {
            return false;  // No existe el centro
        }
        centroTrabajoRepository.deleteById(id);  // Si existe, lo elimina
        return true;  // Eliminado correctamente
    }
}
