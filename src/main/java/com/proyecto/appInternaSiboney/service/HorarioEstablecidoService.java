package com.proyecto.appInternaSiboney.service;


import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoDTO;
import java.util.List;

public interface HorarioEstablecidoService {
    HorarioEstablecidoDTO crearHorarioEstablecido(HorarioEstablecidoCreateDTO horarioCreateDTO);
    HorarioEstablecidoDTO obtenerHorarioEstablecidoPorId(Long id);
    List<HorarioEstablecidoDTO> listarHorariosEstablecidos();
    HorarioEstablecidoDTO actualizarHorarioEstablecido(Long id, HorarioEstablecidoCreateDTO horarioDTO);
    void eliminarHorarioEstablecido(Long id);
    List<HorarioEstablecidoDTO> listarHorariosEstablecidosPorEmpleado(Long empleadoId);
}
