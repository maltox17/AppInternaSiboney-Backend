package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.EmpleadoCentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoCentroDTO;
import com.proyecto.appInternaSiboney.service.EmpleadoCentroService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados-centro")
public class EmpleadoCentroController {

    @Autowired
    private EmpleadoCentroService empleadoCentroService;

    @PostMapping
    public ResponseEntity<EmpleadoCentroDTO> crearEmpleadoCentro(@Valid @RequestBody EmpleadoCentroCreateDTO empleadoCentroCreateDTO) {
        EmpleadoCentroDTO nuevoEmpleadoCentro = empleadoCentroService.crearEmpleadoCentro(empleadoCentroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleadoCentro);
    }

    @GetMapping("/{empleadoId}/{centroId}")
    public ResponseEntity<EmpleadoCentroDTO> obtenerEmpleadoCentro(@PathVariable Long empleadoId, @PathVariable Long centroId) {
        EmpleadoCentroDTO empleadoCentro = empleadoCentroService.obtenerEmpleadoCentro(empleadoId, centroId);
        return ResponseEntity.ok().body(empleadoCentro);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoCentroDTO>> listarEmpleadosCentro() {
        List<EmpleadoCentroDTO> empleadosCentro = empleadoCentroService.listarEmpleadosCentro();
        return ResponseEntity.ok().body(empleadosCentro);
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<EmpleadoCentroDTO>> obtenerCentrosPorEmpleado(@PathVariable Long empleadoId) {
        List<EmpleadoCentroDTO> centros = empleadoCentroService.obtenerCentrosPorEmpleado(empleadoId);
        return ResponseEntity.ok().body(centros);
    }

    @GetMapping("/centro/{centroId}")
    public ResponseEntity<List<EmpleadoCentroDTO>> obtenerEmpleadosPorCentro(@PathVariable Long centroId) {
        List<EmpleadoCentroDTO> empleados = empleadoCentroService.obtenerEmpleadosPorCentro(centroId);
        return ResponseEntity.ok().body(empleados);
    }

    @DeleteMapping("/{empleadoId}/{centroId}")
    public ResponseEntity<Void> eliminarEmpleadoCentro(@PathVariable Long empleadoId, @PathVariable Long centroId) {
        empleadoCentroService.eliminarEmpleadoCentro(empleadoId, centroId);
        return ResponseEntity.noContent().build();
    }
}

