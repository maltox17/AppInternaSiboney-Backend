package com.proyecto.appInternaSiboney.service.impl;


import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoDTO;
import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.entity.DiaSemana;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.HorariosEstablecidos;
import com.proyecto.appInternaSiboney.entity.Turno;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.repository.HorarioEstablecidoRepository;
import com.proyecto.appInternaSiboney.service.HorarioEstablecidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioEstablecidoServiceImpl implements HorarioEstablecidoService {

    @Autowired
    private HorarioEstablecidoRepository horarioEstablecidoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CentroTrabajoRepository centroTrabajoRepository;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public List<HorarioEstablecidoDTO> listarHorariosEstablecidos() {
        return horarioEstablecidoRepository.findAll().stream()
                .map(this::convertirAHorarioDTO)
                .toList();
    }

    @Override
    public HorarioEstablecidoDTO obtenerHorarioEstablecidoPorId(Long id) {
        HorariosEstablecidos horario = horarioEstablecidoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return convertirAHorarioDTO(horario);
    }

    @Override
    public HorarioEstablecidoDTO crearHorarioEstablecido(HorarioEstablecidoCreateDTO horarioCreateDTO) {
        Empleado empleado = empleadoRepository.findById(horarioCreateDTO.getEmpleadoId())
                .orElseThrow(IdNotFoundException::new);
        CentroTrabajo centroTrabajo = centroTrabajoRepository.findById(horarioCreateDTO.getCentroTrabajoId())
                .orElseThrow(IdNotFoundException::new);

        HorariosEstablecidos horario = new HorariosEstablecidos();
        horario.setEmpleado(empleado);
        horario.setCentroTrabajo(centroTrabajo);
        horario.setDiaSemana(DiaSemana.valueOf(horarioCreateDTO.getDiaSemana()));
        horario.setHoraEntrada(horarioCreateDTO.getHoraEntrada());
        horario.setHoraSalida(horarioCreateDTO.getHoraSalida());
        horario.setTurno(Turno.valueOf(horarioCreateDTO.getTurno()));

        HorariosEstablecidos horarioGuardado = horarioEstablecidoRepository.save(horario);
        return convertirAHorarioDTO(horarioGuardado);
    }

    @Override
    public HorarioEstablecidoDTO actualizarHorarioEstablecido(Long id, HorarioEstablecidoCreateDTO horarioDTO) {
        HorariosEstablecidos horarioExistente = horarioEstablecidoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        horarioExistente.setDiaSemana(DiaSemana.valueOf(horarioDTO.getDiaSemana()));
        horarioExistente.setHoraEntrada(horarioDTO.getHoraEntrada());
        horarioExistente.setHoraSalida(horarioDTO.getHoraSalida());
        horarioExistente.setTurno(Turno.valueOf(horarioDTO.getTurno()));

        HorariosEstablecidos horarioActualizado = horarioEstablecidoRepository.save(horarioExistente);
        return convertirAHorarioDTO(horarioActualizado);
    }

    @Override
    public void eliminarHorarioEstablecido(Long id) {
        HorariosEstablecidos horario = horarioEstablecidoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        horarioEstablecidoRepository.delete(horario);
    }

    @Override
    public List<HorarioEstablecidoDTO> listarHorariosEstablecidosPorEmpleado(Long empleadoId) {

        

        List<HorariosEstablecidos> horarios = horarioEstablecidoRepository.findByEmpleadoId(empleadoId);

        return horarios.stream()
                .map(this::convertirAHorarioDTO)
                .collect(Collectors.toList());   
    }
                

    private HorarioEstablecidoDTO convertirAHorarioDTO(HorariosEstablecidos horario) {
        HorarioEstablecidoDTO dto = new HorarioEstablecidoDTO();
        dto.setId(horario.getId());
        dto.setDiaSemana(horario.getDiaSemana().name());
        dto.setHoraEntrada(horario.getHoraEntrada());
        dto.setHoraSalida(horario.getHoraSalida());
        dto.setTurno(horario.getTurno().name());
        dto.setEmpleadoId(horario.getEmpleado().getId());
        dto.setCentroTrabajoId(horario.getCentroTrabajo().getId());
        dto.setEmpleadoNombre(horario.getEmpleado().getNombre());
        dto.setCentroNombre(horario.getCentroTrabajo().getNombre());
        return dto;
    }
}


