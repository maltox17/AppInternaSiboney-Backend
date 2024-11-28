package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.CentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.CentroDTO;
import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.service.CentroTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {

    @Autowired
    private CentroTrabajoRepository centroTrabajoRepository;

    @Override
    public List<CentroDTO> listarCentrosTrabajo() {
        return centroTrabajoRepository.findAll().stream()
                .map(this::convertirCentroADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CentroDTO obtenerCentroTrabajoPorId(Long id) {
        CentroTrabajo centro = centroTrabajoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return convertirCentroADTO(centro);
    }

    @Override
    public CentroDTO crearCentroTrabajo(CentroCreateDTO centroCreateDTO) {
        CentroTrabajo centro = new CentroTrabajo();
        centro.setNombre(centroCreateDTO.getNombre());
        centro.setDireccion(centroCreateDTO.getDireccion());

        CentroTrabajo centroGuardado = centroTrabajoRepository.save(centro);
        return convertirCentroADTO(centroGuardado);
    }

    @Override
    public CentroDTO actualizarCentroTrabajo(Long id, CentroCreateDTO centroCreateDTO) {
        CentroTrabajo centroExistente = centroTrabajoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        centroExistente.setNombre(centroCreateDTO.getNombre());
        centroExistente.setDireccion(centroCreateDTO.getDireccion());

        CentroTrabajo centroActualizado = centroTrabajoRepository.save(centroExistente);
        return convertirCentroADTO(centroActualizado);
    }

    @Override
    public void eliminarCentroTrabajo(Long id) {
        CentroTrabajo centro = centroTrabajoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        centroTrabajoRepository.delete(centro);
    }

    private CentroDTO convertirCentroADTO(CentroTrabajo centro) {
        CentroDTO centroDTO = new CentroDTO();
        centroDTO.setId(centro.getId());
        centroDTO.setNombre(centro.getNombre());
        centroDTO.setDireccion(centro.getDireccion());
        return centroDTO;
    }
}

