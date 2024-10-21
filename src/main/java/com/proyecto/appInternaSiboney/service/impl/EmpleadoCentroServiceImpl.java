package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.EmpleadoCentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoCentroDTO;
import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.EmpleadoCentro;
import com.proyecto.appInternaSiboney.entity.EmpleadoCentroPK;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.repository.EmpleadoCentroRepository;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.service.EmpleadoCentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoCentroServiceImpl implements EmpleadoCentroService {

    @Autowired
    private EmpleadoCentroRepository empleadoCentroRepository;
    private EmpleadoRepository empleadoRepository;
    private CentroTrabajoRepository centroTrabajoRepository;

    @Override
    public EmpleadoCentroDTO crearEmpleadoCentro(EmpleadoCentroCreateDTO empleadoCentroCreateDTO) {
    // Obtener las entidades Empleado y CentroTrabajo usando los repositorios
    Empleado empleado = empleadoRepository.findById(empleadoCentroCreateDTO.getEmpleadoId())
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    CentroTrabajo centroTrabajo = centroTrabajoRepository.findById(empleadoCentroCreateDTO.getCentroTrabajoId())
            .orElseThrow(() -> new RuntimeException("Centro de trabajo no encontrado"));

    // Asignar las entidades a la nueva instancia de EmpleadoCentro
    EmpleadoCentro empleadoCentro = new EmpleadoCentro();
    empleadoCentro.setEmpleado(empleado);
    empleadoCentro.setCentroTrabajo(centroTrabajo);
    empleadoCentro.setEsEncargado(empleadoCentroCreateDTO.isEsEncargado());

    // Guardar en el repositorio
    EmpleadoCentro empleadoCentroGuardado = empleadoCentroRepository.save(empleadoCentro);
    return convertirAEmpleadoCentroDTO(empleadoCentroGuardado);
}

    @Override
    public EmpleadoCentroDTO obtenerEmpleadoCentro(Long empleadoId, Long centroId) {
        Optional<EmpleadoCentro> empleadoCentro = empleadoCentroRepository.findById(new EmpleadoCentroPK(empleadoId, centroId));
        return empleadoCentro.map(this::convertirAEmpleadoCentroDTO).orElse(null);
    }

    @Override
    public List<EmpleadoCentroDTO> listarEmpleadosCentro() {
        List<EmpleadoCentro> empleadosCentro = empleadoCentroRepository.findAll();
        List<EmpleadoCentroDTO> empleadosCentroDTO = new ArrayList<>();

        for (EmpleadoCentro empleadoCentro : empleadosCentro) {
            empleadosCentroDTO.add(convertirAEmpleadoCentroDTO(empleadoCentro));
        }

        return empleadosCentroDTO;
    }

    @Override
    public List<EmpleadoCentroDTO> obtenerCentrosPorEmpleado(Long empleadoId) {
        List<EmpleadoCentro> empleadosCentro = empleadoCentroRepository.findByEmpleadoId(empleadoId);
        List<EmpleadoCentroDTO> empleadosCentroDTO = new ArrayList<>();

        for (EmpleadoCentro empleadoCentro : empleadosCentro) {
            empleadosCentroDTO.add(convertirAEmpleadoCentroDTO(empleadoCentro));
        }

        return empleadosCentroDTO;
    }

    @Override
    public List<EmpleadoCentroDTO> obtenerEmpleadosPorCentro(Long centroId) {
        List<EmpleadoCentro> empleadosCentro = empleadoCentroRepository.findByCentroTrabajoId(centroId);
        List<EmpleadoCentroDTO> empleadosCentroDTO = new ArrayList<>();

        for (EmpleadoCentro empleadoCentro : empleadosCentro) {
            empleadosCentroDTO.add(convertirAEmpleadoCentroDTO(empleadoCentro));
        }

        return empleadosCentroDTO;
    }

    @Override
    public boolean eliminarEmpleadoCentro(Long empleadoId, Long centroId) {
        EmpleadoCentroPK empleadoCentroPK = new EmpleadoCentroPK(empleadoId, centroId);
        if (empleadoCentroRepository.existsById(empleadoCentroPK)) {
            empleadoCentroRepository.deleteById(empleadoCentroPK);
            return true;
        }
        return false;
    }

    private EmpleadoCentroDTO convertirAEmpleadoCentroDTO(EmpleadoCentro empleadoCentro) {
        EmpleadoCentroDTO dto = new EmpleadoCentroDTO();
        dto.setEmpleadoId(empleadoCentro.getEmpleado().getId());
        dto.setCentroTrabajoId(empleadoCentro.getCentroTrabajo().getId());
        dto.setEsEncargado(empleadoCentro.isEsEncargado());
        return dto;
    }
}
