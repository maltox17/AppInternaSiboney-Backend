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

    private final CentroTrabajoRepository centroTrabajoRepository;

    @Autowired
    public CentroTrabajoServiceImpl(CentroTrabajoRepository centroTrabajoRepository) {
        this.centroTrabajoRepository = centroTrabajoRepository;
    }

    @Override
    public CentroTrabajo crearCentroTrabajo(CentroTrabajo centroTrabajo) {
        return centroTrabajoRepository.save(centroTrabajo);
    }


    @Override
    public CentroTrabajo obtenerCentroTrabajoPorId(Long id) {
        return centroTrabajoRepository.findById(id).orElse(null);
    }

    @Override
    public List<CentroTrabajo> listarCentrosTrabajo() {
        return centroTrabajoRepository.findAll();
    }

    @Override
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
    

    @Override
    public void eliminarCentroTrabajo(Long id) {
        centroTrabajoRepository.deleteById(id);
    }
}
