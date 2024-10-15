package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.VacacionesDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Vacaciones;
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
    VacacionesRepository vacacionesRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;



    public VacacionesDTO crearVacaciones(VacacionesDTO vacacionesDTO) {
        Vacaciones vacaciones = new Vacaciones();
        vacaciones.setFechaInicio(vacacionesDTO.getFechaInicio());
        vacaciones.setFechaFin(vacacionesDTO.getFechaFin());
        vacaciones.setEstado(vacacionesDTO.getEstado());

        // Buscar empleado por ID y asignar la relación
        Empleado empleado = empleadoRepository.findById(vacacionesDTO.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        vacaciones.setEmpleado(empleado);

        Vacaciones vacacionesGuardadas = vacacionesRepository.save(vacaciones);
        return convertirAVacacionesDTO(vacacionesGuardadas);
    }


    public VacacionesDTO obtenerVacacionesPorId(Long id) {
        Optional<Vacaciones> vacaciones = vacacionesRepository.findById(id);
        return vacaciones.map(this::convertirAVacacionesDTO).orElse(null);
    }


    public List<VacacionesDTO> listarVacaciones() {
        return vacacionesRepository.findAll().stream()
                .map(this::convertirAVacacionesDTO)
                .collect(Collectors.toList());
    }


    public VacacionesDTO actualizarVacaciones(Long id, VacacionesDTO vacacionesDTO) {
        Vacaciones vacacionesExistente = vacacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacaciones no encontradas"));

        vacacionesExistente.setFechaInicio(vacacionesDTO.getFechaInicio());
        vacacionesExistente.setFechaFin(vacacionesDTO.getFechaFin());
        vacacionesExistente.setEstado(vacacionesDTO.getEstado());

        Vacaciones vacacionesActualizadas = vacacionesRepository.save(vacacionesExistente);
        return convertirAVacacionesDTO(vacacionesActualizadas);
    }

    public boolean eliminarVacaciones(Long id) {

        if (!vacacionesRepository.existsById(id)) {
            return false;
        }
        vacacionesRepository.deleteById(id);
        return true;
    }


    public List<VacacionesDTO> listarVacacionesPorEmpleado(Long empleadoId) {
        List<Vacaciones> vacacionesList = vacacionesRepository.findByEmpleadoId(empleadoId);
        List<VacacionesDTO> vacacionesDTOList = new ArrayList<>();
    
        for (Vacaciones vacaciones : vacacionesList) {
            VacacionesDTO dto = convertirAVacacionesDTO(vacaciones);
            vacacionesDTOList.add(dto);
        }
    
        return vacacionesDTOList;
    }
    

    public List<VacacionesDTO> obtenerVacacionesPorAno(Integer year) {
        List<Vacaciones> vacaciones;
    
        if (year != null) {
            // Si se pasa el año, filtra por el año
            vacaciones = vacacionesRepository.findByAno(year);
        } else {
            // Devuelve una lista vacía si no se pasa el año
            return new ArrayList<>();
        }
    
        // Crear una lista de VacacionesDTO para almacenar los resultados convertidos
        List<VacacionesDTO> vacacionesDTOList = new ArrayList<>();
    
        // Convertir cada Vacaciones en VacacionesDTO utilizando el método convertirAVacacionesDTO
        for (Vacaciones vacacion : vacaciones) {
            VacacionesDTO dto = convertirAVacacionesDTO(vacacion);
            vacacionesDTOList.add(dto);
        }
    
        return vacacionesDTOList;
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
