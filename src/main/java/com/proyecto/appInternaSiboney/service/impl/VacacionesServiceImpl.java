package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.VacacionesDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Vacaciones;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.repository.VacacionesRepository;
import com.proyecto.appInternaSiboney.service.VacacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para gestionar las vacaciones de los empleados.
 */
@Service
public class VacacionesServiceImpl implements VacacionesService {

    @Autowired
    private VacacionesRepository vacacionesRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<VacacionesDTO> listarVacaciones() {
        return vacacionesRepository.findAll().stream()
                .map(this::convertirAVacacionesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VacacionesDTO obtenerVacacionesPorId(Long id) {
        Vacaciones vacaciones = vacacionesRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return convertirAVacacionesDTO(vacaciones);
    }

    @Override
    public VacacionesDTO crearVacaciones(VacacionesDTO vacacionesDTO) {
        Vacaciones vacaciones = new Vacaciones();
        vacaciones.setFechaInicio(vacacionesDTO.getFechaInicio());
        vacaciones.setFechaFin(vacacionesDTO.getFechaFin());
        vacaciones.setEstado(vacacionesDTO.getEstado());

        // Buscar empleado por ID y asignar la relación
        Empleado empleado = empleadoRepository.findById(vacacionesDTO.getEmpleadoId())
                .orElseThrow(IdNotFoundException::new);
        vacaciones.setEmpleado(empleado);

        Vacaciones vacacionesGuardadas = vacacionesRepository.save(vacaciones);
        return convertirAVacacionesDTO(vacacionesGuardadas);
    }

    @Override
    public VacacionesDTO actualizarVacaciones(Long id, VacacionesDTO vacacionesDTO) {
        Vacaciones vacacionesExistente = vacacionesRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        vacacionesExistente.setFechaInicio(vacacionesDTO.getFechaInicio());
        vacacionesExistente.setFechaFin(vacacionesDTO.getFechaFin());
        vacacionesExistente.setEstado(vacacionesDTO.getEstado());

        Vacaciones vacacionesActualizadas = vacacionesRepository.save(vacacionesExistente);
        return convertirAVacacionesDTO(vacacionesActualizadas);
    }

    @Override
    public void eliminarVacaciones(Long id) {
        Vacaciones vacaciones = vacacionesRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        vacacionesRepository.delete(vacaciones);
    }

    @Override
    public List<VacacionesDTO> listarVacacionesPorEmpleado(Long empleadoId) {
        List<Vacaciones> vacacionesList = vacacionesRepository.findByEmpleadoId(empleadoId);
        return vacacionesList.stream()
                .map(this::convertirAVacacionesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VacacionesDTO> obtenerVacacionesPorAno(Integer year) {
        List<Vacaciones> vacaciones = (year != null) ? vacacionesRepository.findByAno(year) : new ArrayList<>();
        return vacaciones.stream()
                .map(this::convertirAVacacionesDTO)
                .collect(Collectors.toList());
    }

    private VacacionesDTO convertirAVacacionesDTO(Vacaciones vacaciones) {
        VacacionesDTO dto = new VacacionesDTO();
        dto.setId(vacaciones.getId());
        dto.setFechaInicio(vacaciones.getFechaInicio());
        dto.setFechaFin(vacaciones.getFechaFin());
        dto.setEstado(vacaciones.getEstado());
        dto.setEmpleadoId(vacaciones.getEmpleado().getId());
        return dto;
    }
}
