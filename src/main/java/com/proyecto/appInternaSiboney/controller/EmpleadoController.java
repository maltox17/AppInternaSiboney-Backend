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
     private EmpleadoService empleadoService;
 
     @PostMapping
     public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoCreateDTO empleadoCreateDTO) {
         EmpleadoDTO nuevoEmpleado = empleadoService.crearEmpleado(empleadoCreateDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
     }
 
     @GetMapping("/{id}")
     public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable Long id) {
         EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorId(id);
         return ResponseEntity.ok().body(empleado);
     }
 
     @GetMapping
     public ResponseEntity<List<EmpleadoDTO>> listarEmpleados() {
         List<EmpleadoDTO> empleados = empleadoService.listarEmpleados();
         return ResponseEntity.ok().body(empleados);
     }
 
     @PutMapping("/{id}")
     public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id,
                                                           @Valid @RequestBody EmpleadoCreateDTO empleadoDTO) {
         EmpleadoDTO empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDTO);
         return ResponseEntity.ok().body(empleadoActualizado);
     }
 
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
         empleadoService.eliminarEmpleado(id);
         return ResponseEntity.noContent().build();
     }
 
     @GetMapping("/rol/{rol}")
     public ResponseEntity<List<EmpleadoDTO>> buscarEmpleadosPorRol(@PathVariable Rol rol) {
         List<EmpleadoDTO> empleados = empleadoService.buscarEmpleadosPorRol(rol);
         if (empleados.isEmpty()) {
             return ResponseEntity.noContent().build();
         }
         return ResponseEntity.ok().body(empleados);
     }
 }
 