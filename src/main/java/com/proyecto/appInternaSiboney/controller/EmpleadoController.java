package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.EmpleadoCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoDTO;
import com.proyecto.appInternaSiboney.entity.Rol;
import com.proyecto.appInternaSiboney.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los empleados.
 * Proporciona endpoints para crear, actualizar, eliminar y consultar empleados.
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;


    /**
     * Endpoint para crear un nuevo empleado.
     *
     * @param empleadoCreateDTO Objeto que contiene los datos del empleado a crear.
     * @return ResponseEntity con el empleado creado y un código de estado 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoCreateDTO empleadoCreateDTO) {
        EmpleadoDTO nuevoEmpleado = empleadoService.crearEmpleado(empleadoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    /**
     * Endpoint para obtener un empleado por su ID.
     *
     * @param id ID del empleado a obtener.
     * @return ResponseEntity con los datos del empleado y un código de estado 200 (OK), o 404 (NOT FOUND) si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable Long id) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorId(id);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
    }

    /**
     * Endpoint para listar todos los empleados.
     *
     * @return Lista de empleados en formato DTO.
     */
    @GetMapping
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    /**
     * Endpoint para actualizar un empleado existente.
     *
     * @param id ID del empleado a actualizar.
     * @param empleadoDTO Objeto que contiene los nuevos datos del empleado.
     * @return ResponseEntity con el empleado actualizado y un código de estado 200 (OK), o 404 (NOT FOUND) si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDTO);
        if (empleadoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoActualizado);
    }

    /**
     * Endpoint para eliminar un empleado por su ID.
     *
     * @param id ID del empleado a eliminar.
     * @return ResponseEntity con un código de estado 204 (NO CONTENT).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        if(empleadoService.eliminarEmpleado(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para buscar empleados por su rol.
     *
     * @param rol Rol del empleado a buscar (puede ser EMPLEADO, ENCARGADO, o JEFE).
     * @return ResponseEntity con una lista de empleados que tienen el rol especificado y un código de estado 200 (OK),
     *         o 204 (NO CONTENT) si no se encuentran empleados con ese rol.
     */
    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<EmpleadoDTO>> buscarEmpleadosPorRol(@PathVariable Rol rol) {
        List<EmpleadoDTO> empleados = empleadoService.buscarEmpleadosPorRol(rol);
        if (empleados.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay resultados
        }
        return ResponseEntity.ok(empleados);  // 200 OK con la lista de empleados
    }
}
