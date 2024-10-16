package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.HorarioDTO;
import com.proyecto.appInternaSiboney.entity.Turno;

import java.util.List;

public interface HorarioService {
    HorarioDTO crearHorario(HorarioDTO horarioDTO);
    HorarioDTO obtenerHorarioPorId(Long id);
    List<HorarioDTO> listarHorarios();
    HorarioDTO actualizarHorario(Long id, HorarioDTO horarioDTO);
    boolean eliminarHorario(Long id);
    List<HorarioDTO> listarHorariosPorSemanaCentroYTurno(int semana, Long centroTrabajoId, Turno turno);
    List<HorarioDTO> listarHorariosPorEmpleado(Long empleadoId);
}
