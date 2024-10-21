package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoDTO;
import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.entity.DiaSemana;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.HorariosEstablecidos;
import com.proyecto.appInternaSiboney.entity.Turno;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.repository.HorarioEstablecidoRepository;
import com.proyecto.appInternaSiboney.service.HorarioEstablecidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioEstablecidoServiceImpl implements HorarioEstablecidoService {

    @Autowired
    private HorarioEstablecidoRepository horarioEstablecidoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CentroTrabajoRepository centroTrabajoRepository;

    @Override
    public HorarioEstablecidoDTO crearHorarioEstablecido(HorarioEstablecidoCreateDTO horarioCreateDTO) {
        HorariosEstablecidos horario = new HorariosEstablecidos();
        Optional<Empleado> empleado = empleadoRepository.findById(horarioCreateDTO.getEmpleadoId());
        Optional<CentroTrabajo> centro = centroTrabajoRepository.findById(horarioCreateDTO.getCentroTrabajoId());

        if (empleado.isPresent() && centro.isPresent()) {
            horario.setEmpleado(empleado.get());
            horario.setCentroTrabajo(centro.get());
            horario.setDiaSemana(DiaSemana.valueOf(horarioCreateDTO.getDiaSemana()));
            horario.setHoraEntrada(horarioCreateDTO.getHoraEntrada());
            horario.setHoraSalida(horarioCreateDTO.getHoraSalida());
            horario.setTurno(Turno.valueOf(horarioCreateDTO.getTurno()));

            HorariosEstablecidos horarioGuardado = horarioEstablecidoRepository.save(horario);
            return convertirAHorarioDTO(horarioGuardado);
        }

        return null;  // Manejar la situación cuando no se encuentra empleado o centro
    }

    @Override
    public HorarioEstablecidoDTO obtenerHorarioEstablecidoPorId(Long id) {
        Optional<HorariosEstablecidos> horario = horarioEstablecidoRepository.findById(id);
        return horario.map(this::convertirAHorarioDTO).orElse(null);
    }

    @Override
    public List<HorarioEstablecidoDTO> listarHorariosEstablecidos() {
        List<HorariosEstablecidos> horarios = horarioEstablecidoRepository.findAll();
        List<HorarioEstablecidoDTO> horariosDTO = new ArrayList<>();

        for (HorariosEstablecidos horario : horarios) {
            horariosDTO.add(convertirAHorarioDTO(horario));
        }

        return horariosDTO;
    }

    @Override
    public HorarioEstablecidoDTO actualizarHorarioEstablecido(Long id, HorarioEstablecidoDTO horarioDTO) {
        Optional<HorariosEstablecidos> horarioExistente = horarioEstablecidoRepository.findById(id);

        if (horarioExistente.isPresent()) {
            HorariosEstablecidos horario = horarioExistente.get();
            horario.setDiaSemana(DiaSemana.valueOf(horarioDTO.getDiaSemana()));
            horario.setHoraEntrada(horarioDTO.getHoraEntrada());
            horario.setHoraSalida(horarioDTO.getHoraSalida());
            horario.setTurno(Turno.valueOf(horarioDTO.getTurno()));
            
            HorariosEstablecidos horarioActualizado = horarioEstablecidoRepository.save(horario);
            return convertirAHorarioDTO(horarioActualizado);
        }

        return null;
    }

    @Override
    public boolean eliminarHorarioEstablecido(Long id) {
        if (!horarioEstablecidoRepository.existsById(id)) {
            return false;
        }
        horarioEstablecidoRepository.deleteById(id);
        return true;
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
        return dto;
    }
}

