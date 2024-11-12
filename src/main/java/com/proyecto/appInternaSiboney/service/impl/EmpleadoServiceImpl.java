package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.EmpleadoCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoEditDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Rol;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.service.EmpleadoService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


/**
 * Servicio para gestionar la lógica de negocio relacionada con los empleados.
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private final PasswordEncoder passwordEncoder;

    public EmpleadoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(this::convertirAEmpleadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return convertirAEmpleadoDTO(empleado);
    }

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoCreateDTO empleadoCreateDTO) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoCreateDTO.getNombre());
        empleado.setEmail(empleadoCreateDTO.getEmail());
        empleado.setTelefono(empleadoCreateDTO.getTelefono());
        empleado.setHorasContrato(empleadoCreateDTO.getHorasContrato());
        empleado.setRol(empleadoCreateDTO.getRol());

        // Cifrar la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(empleadoCreateDTO.getClave());
        empleado.setClave(hashedPassword);

        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return convertirAEmpleadoDTO(empleadoGuardado);
    }

    @Override
    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoEditDTO empleadoDTO) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);

        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setEmail(empleadoDTO.getEmail());
        empleadoExistente.setTelefono(empleadoDTO.getTelefono());
        empleadoExistente.setHorasContrato(empleadoDTO.getHorasContrato());
        empleadoExistente.setRol(empleadoDTO.getRol());

        Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
        return convertirAEmpleadoDTO(empleadoActualizado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        empleadoRepository.delete(empleado);
    }

    @Override
    public List<EmpleadoDTO> buscarEmpleadosPorRol(Rol rol) {
        return empleadoRepository.findByRol(rol).stream()
                .map(this::convertirAEmpleadoDTO)
                .collect(Collectors.toList());
    }

    private EmpleadoDTO convertirAEmpleadoDTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setEmail(empleado.getEmail());
        dto.setTelefono(empleado.getTelefono());
        dto.setHorasContrato(empleado.getHorasContrato());
        dto.setRol(empleado.getRol());
        return dto;
    }
}
