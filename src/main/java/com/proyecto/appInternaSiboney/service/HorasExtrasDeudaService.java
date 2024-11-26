package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaDTO;
import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaEditDTO;

import java.util.List;

public interface HorasExtrasDeudaService {
    HorasExtrasDeudaDTO crearHorasExtrasDeuda(HorasExtrasDeudaCreateDTO dto);

    HorasExtrasDeudaDTO obtenerPorId(Long id);

    List<HorasExtrasDeudaDTO> listarHorasExtrasDeuda();

    HorasExtrasDeudaDTO actualizarHorasExtrasDeuda(Long id, HorasExtrasDeudaEditDTO dto);

    void eliminarHorasExtrasDeuda(Long id);

    List<HorasExtrasDeudaDTO> buscarPorEmpleadoId(Long empleadoId);
}
