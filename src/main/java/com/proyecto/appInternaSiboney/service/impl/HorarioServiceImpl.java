package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.HorarioDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Horario;
import com.proyecto.appInternaSiboney.entity.Turno;
import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.repository.HorarioRepository;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    HorarioRepository horarioRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    CentroTrabajoRepository centroTrabajoRepository;

    public HorarioDTO crearHorario(HorarioDTO horarioDTO) {
        Horario horario = new Horario();
        horario.setFecha(horarioDTO.getFecha());
        horario.setHoraEntrada(horarioDTO.getHoraEntrada());
        horario.setHoraSalida(horarioDTO.getHoraSalida());
        horario.setTurno(horarioDTO.getTurno());  // Nuevo campo Turno

        Empleado empleado = empleadoRepository.findById(horarioDTO.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        horario.setEmpleado(empleado);

        CentroTrabajo centroTrabajo = centroTrabajoRepository.findById(horarioDTO.getCentroTrabajoId())
                .orElseThrow(() -> new RuntimeException("Centro de trabajo no encontrado"));
        horario.setCentroTrabajo(centroTrabajo);

        Horario horarioGuardado = horarioRepository.save(horario);
        return convertirAHorarioDTO(horarioGuardado);
    }

    public HorarioDTO obtenerHorarioPorId(Long id) {
        Optional<Horario> horario = horarioRepository.findById(id);
        if (horario.isPresent()) {
            return convertirAHorarioDTO(horario.get());
        } else {
            return null;
        }
    }

    public List<HorarioDTO> listarHorarios() {
        List<Horario> horarios = horarioRepository.findAll();
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for (Horario horario : horarios) {
            horariosDTO.add(convertirAHorarioDTO(horario));
        }

        return horariosDTO;
    }

    public HorarioDTO actualizarHorario(Long id, HorarioDTO horarioDTO) {
        Horario horarioExistente = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        horarioExistente.setFecha(horarioDTO.getFecha());
        horarioExistente.setHoraEntrada(horarioDTO.getHoraEntrada());
        horarioExistente.setHoraSalida(horarioDTO.getHoraSalida());
        horarioExistente.setTurno(horarioDTO.getTurno());  // Actualizar turno

        Empleado empleado = empleadoRepository.findById(horarioDTO.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        horarioExistente.setEmpleado(empleado);

        CentroTrabajo centroTrabajo = centroTrabajoRepository.findById(horarioDTO.getCentroTrabajoId())
                .orElseThrow(() -> new RuntimeException("Centro de trabajo no encontrado"));
        horarioExistente.setCentroTrabajo(centroTrabajo);

        Horario horarioActualizado = horarioRepository.save(horarioExistente);
        return convertirAHorarioDTO(horarioActualizado);
    }

    public boolean eliminarHorario(Long id) {
        if (!horarioRepository.existsById(id)) {
            return false;
        }
        horarioRepository.deleteById(id);
        return true;
    }

    public List<HorarioDTO> listarHorariosPorEmpleado(Long empleadoId) {
        List<Horario> horarios = horarioRepository.findByEmpleadoId(empleadoId);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for (Horario horario : horarios) {
            horariosDTO.add(convertirAHorarioDTO(horario));
        }

        return horariosDTO;
    }

    public List<HorarioDTO> listarHorariosPorSemanaCentroYTurno(int semana, Long centroTrabajoId, Turno turno) {
        List<Horario> horarios = horarioRepository.findBySemanaCentroYTurno(semana, centroTrabajoId, turno);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for (Horario horario : horarios) {
            horariosDTO.add(convertirAHorarioDTO(horario));
        }

        return horariosDTO;
    }

    private HorarioDTO convertirAHorarioDTO(Horario horario) {
        HorarioDTO dto = new HorarioDTO();
        dto.setId(horario.getId());
        dto.setFecha(horario.getFecha());
        dto.setHoraEntrada(horario.getHoraEntrada());
        dto.setHoraSalida(horario.getHoraSalida());
        dto.setTurno(horario.getTurno());  // Asignar turno al DTO
        dto.setEmpleadoId(horario.getEmpleado().getId());
        dto.setCentroTrabajoId(horario.getCentroTrabajo().getId());
        return dto;
    }
}