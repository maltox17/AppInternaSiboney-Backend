package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.EmpleadoCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Rol;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.service.EmpleadoService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar la lógica de negocio relacionada con los empleados.
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    
    public EmpleadoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Crea un nuevo empleado.
     * 
     * @param empleadoCreateDTO Datos del empleado a crear.
     * @return El empleado creado.
     */

    public EmpleadoDTO crearEmpleado(EmpleadoCreateDTO empleadoCreateDTO) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoCreateDTO.getNombre());
        empleado.setEmail(empleadoCreateDTO.getEmail());
        empleado.setTelefono(empleadoCreateDTO.getTelefono());
        empleado.setHorasContrato(empleadoCreateDTO.getHorasContrato());
        empleado.setRol(empleadoCreateDTO.getRol());

        // Hash de la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(empleadoCreateDTO.getClave());
        empleado.setClave(hashedPassword); // Guardar la clave cifrada

        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return convertirAEmpleadoDTO(empleadoGuardado);
    }

    /**
     * Obtiene un empleado por su ID.
     * 
     * @param id ID del empleado.
     * @return El empleado correspondiente al ID.
     */

     public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        
        if (empleado.isPresent()) {
            return convertirAEmpleadoDTO(empleado.get());
        } else {
            return null;
        }
    }
    

    /**
     * Lista todos los empleados.
     * 
     * @return Lista de empleados en forma de DTO.
     */
   
     public List<EmpleadoDTO> listarEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoDTO> empleadosDTO = new ArrayList<>();
        
        for (Empleado empleado : empleados) {
            EmpleadoDTO empleadoDTO = convertirAEmpleadoDTO(empleado);
            empleadosDTO.add(empleadoDTO);
        }
        
        return empleadosDTO;
    }
    

    /**
     * Actualiza un empleado existente.
     * 
     * @param id          ID del empleado a actualizar.
     * @param empleadoDTO Datos actualizados del empleado.
     * @return El empleado actualizado en forma de DTO.
     */

    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setEmail(empleadoDTO.getEmail());
        empleadoExistente.setTelefono(empleadoDTO.getTelefono());
        empleadoExistente.setHorasContrato(empleadoDTO.getHorasContrato());
        empleadoExistente.setRol(empleadoDTO.getRol());

        Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
        return convertirAEmpleadoDTO(empleadoActualizado);
    }

    /**
     * Elimina un empleado por su ID.
     * 
     * @param id ID del empleado a eliminar.
     */

     public boolean eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            return false;  // No existe el empleado
        }
        empleadoRepository.deleteById(id);  // Si existe, lo elimina
        return true;  // Eliminado correctamente
    }

    /**
     * Busca empleados por su rol.
     * 
     * @param rol Rol de los empleados a buscar.
     * @return Lista de empleados con el rol especificado.
     */

    public List<EmpleadoDTO> buscarEmpleadosPorRol(Rol rol) {
        List<Empleado> empleados = empleadoRepository.findByRol(rol);
        List<EmpleadoDTO> empleadosDTO = new ArrayList<>();

        for (Empleado empleado : empleados) {
            empleadosDTO.add(convertirAEmpleadoDTO(empleado));
        }

        return empleadosDTO;
    }

    /**
     * Convierte una entidad Empleado a un DTO.
     * 
     * @param empleado Entidad Empleado.
     * @return DTO de Empleado.
     */
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