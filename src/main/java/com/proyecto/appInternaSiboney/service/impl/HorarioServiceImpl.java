package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.HorarioCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioDTO;
import com.proyecto.appInternaSiboney.dto.HorarioNombresDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Horario;
import com.proyecto.appInternaSiboney.entity.Turno;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
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
import java.util.stream.Collectors;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CentroTrabajoRepository centroTrabajoRepository;

    @Override
    public List<HorarioDTO> listarHorarios() {
        return horarioRepository.findAll().stream()
                .map(this::convertirAHorarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HorarioDTO obtenerHorarioPorId(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return convertirAHorarioDTO(horario);
    }

    @Override
    public HorarioDTO crearHorario(HorarioCreateDTO horarioDTO) {
        Horario horario = new Horario();
        horario.setFecha(horarioDTO.getFecha());
        horario.setHoraEntrada(horarioDTO.getHoraEntrada());
        horario.setHoraSalida(horarioDTO.getHoraSalida());
        horario.setTurno(horarioDTO.getTurno());

        Empleado empleado = empleadoRepository.findById(horarioDTO.getEmpleadoId())
                .orElseThrow(IdNotFoundException::new);
        horario.setEmpleado(empleado);

        CentroTrabajo centroTrabajo = centroTrabajoRepository.findById(horarioDTO.getCentroTrabajoId())
                .orElseThrow(IdNotFoundException::new);
        horario.setCentroTrabajo(centroTrabajo);

        Horario horarioGuardado = horarioRepository.save(horario);
        return convertirAHorarioDTO(horarioGuardado);
    }

    @Override
    public HorarioDTO actualizarHorario(Long id, HorarioCreateDTO horarioDTO) {
        Horario horarioExistente = horarioRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        horarioExistente.setFecha(horarioDTO.getFecha());
        horarioExistente.setHoraEntrada(horarioDTO.getHoraEntrada());
        horarioExistente.setHoraSalida(horarioDTO.getHoraSalida());
        horarioExistente.setTurno(horarioDTO.getTurno());

        Empleado empleado = empleadoRepository.findById(horarioDTO.getEmpleadoId())
                .orElseThrow(IdNotFoundException::new);
        horarioExistente.setEmpleado(empleado);

        CentroTrabajo centroTrabajo = centroTrabajoRepository.findById(horarioDTO.getCentroTrabajoId())
                .orElseThrow(IdNotFoundException::new);
        horarioExistente.setCentroTrabajo(centroTrabajo);

        Horario horarioActualizado = horarioRepository.save(horarioExistente);
        return convertirAHorarioDTO(horarioActualizado);
    }

    @Override
    public void eliminarHorario(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        horarioRepository.delete(horario);
    }

    @Override
    public List<HorarioDTO> listarHorariosPorEmpleado(Long empleadoId) {
        List<Horario> horarios = horarioRepository.findByEmpleadoId(empleadoId);
        return horarios.stream()
                .map(this::convertirAHorarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HorarioDTO> listarHorariosPorSemanaCentroYTurno(int semana, Long centroTrabajoId, Turno turno) {
        List<Horario> horarios = horarioRepository.findBySemanaCentroYTurno(semana, centroTrabajoId, turno);
        return horarios.stream()
                .map(this::convertirAHorarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HorarioNombresDTO> obtenerHorariosConNombres() {
        List<Horario> horarios = horarioRepository.findWithEmpleadoAndCentroNombres();
        return horarios.stream().map(this::convertirAHorarioNombresDTO).collect(Collectors.toList());
    }

    private HorarioDTO convertirAHorarioDTO(Horario horario) {
        HorarioDTO dto = new HorarioDTO();
        dto.setId(horario.getId());
        dto.setFecha(horario.getFecha());
        dto.setHoraEntrada(horario.getHoraEntrada());
        dto.setHoraSalida(horario.getHoraSalida());
        dto.setTurno(horario.getTurno());
        dto.setEmpleadoId(horario.getEmpleado().getId());
        dto.setCentroTrabajoId(horario.getCentroTrabajo().getId());
        return dto;
    }

    private HorarioNombresDTO convertirAHorarioNombresDTO(Horario horario) {
        HorarioNombresDTO dto = new HorarioNombresDTO();
        dto.setId(horario.getId());
        dto.setFecha(horario.getFecha());
        dto.setHoraEntrada(horario.getHoraEntrada());
        dto.setHoraSalida(horario.getHoraSalida());
        dto.setTurno(horario.getTurno());
        dto.setEmpleadoId(horario.getEmpleado().getId());
        dto.setEmpleadoNombre(horario.getEmpleado().getNombre()); // Nombre del empleado
        dto.setCentroTrabajoId(horario.getCentroTrabajo().getId());
        dto.setCentroNombre(horario.getCentroTrabajo().getNombre()); // Nombre del centro de trabajo
        return dto;
    }
}
