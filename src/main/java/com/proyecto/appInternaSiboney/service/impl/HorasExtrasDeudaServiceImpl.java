package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaDTO;
import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaEditDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.HorasExtrasDeuda;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.repository.HorasExtrasDeudaRepository;
import com.proyecto.appInternaSiboney.service.HorasExtrasDeudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorasExtrasDeudaServiceImpl implements HorasExtrasDeudaService {

    @Autowired
    private HorasExtrasDeudaRepository repository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public HorasExtrasDeudaDTO crearHorasExtrasDeuda(HorasExtrasDeudaCreateDTO dto) {
        HorasExtrasDeuda horasExtrasDeuda = new HorasExtrasDeuda();


        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
        .orElseThrow(IdNotFoundException::new);

        horasExtrasDeuda.setHorasExtras(dto.getHorasExtras());
        horasExtrasDeuda.setHorasDeuda(dto.getHorasDeuda());
        horasExtrasDeuda.setEmpleado(empleado);

        HorasExtrasDeuda guardado = repository.save(horasExtrasDeuda);
        return convertirAHorasExtrasDeudaDTO(guardado);
    }

    @Override
    public HorasExtrasDeudaDTO obtenerPorId(Long id) {
        HorasExtrasDeuda horasExtrasDeuda = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return convertirAHorasExtrasDeudaDTO(horasExtrasDeuda);
    }

    @Override
    public List<HorasExtrasDeudaDTO> listarHorasExtrasDeuda() {
        return repository.findAll().stream()
                .map(this::convertirAHorasExtrasDeudaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HorasExtrasDeudaDTO actualizarHorasExtrasDeuda(Long id, HorasExtrasDeudaEditDTO dto) {
        HorasExtrasDeuda existente = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        existente.setHorasExtras(dto.getHorasExtras());
        existente.setHorasDeuda(dto.getHorasDeuda());

        HorasExtrasDeuda actualizado = repository.save(existente);
        return convertirAHorasExtrasDeudaDTO(actualizado);
    }

    @Override
    public void eliminarHorasExtrasDeuda(Long id) {
        HorasExtrasDeuda horasExtrasDeuda = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        repository.delete(horasExtrasDeuda);
    }

    @Override
    public List<HorasExtrasDeudaDTO> buscarPorEmpleadoId(Long empleadoId) {
        return repository.findByEmpleado_Id(empleadoId).stream()
                .map(this::convertirAHorasExtrasDeudaDTO)
                .collect(Collectors.toList());
    }

    private HorasExtrasDeudaDTO convertirAHorasExtrasDeudaDTO(HorasExtrasDeuda entity) {
        HorasExtrasDeudaDTO dto = new HorasExtrasDeudaDTO();
        dto.setId(entity.getId());
        dto.setHorasExtras(entity.getHorasExtras());
        dto.setHorasDeuda(entity.getHorasDeuda());
        dto.setEmpleadoId(entity.getEmpleado().getId());
        dto.setEmpleadoNombre(entity.getEmpleado().getNombre());
        return dto;
    }
}
