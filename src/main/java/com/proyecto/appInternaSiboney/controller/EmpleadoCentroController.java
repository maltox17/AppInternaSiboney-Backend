package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.EmpleadoCentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoCentroDTO;
import com.proyecto.appInternaSiboney.service.EmpleadoCentroService;
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
    public ResponseEntity<EmpleadoCentroDTO> crearEmpleadoCentro(@RequestBody EmpleadoCentroCreateDTO empleadoCentroCreateDTO) {
        EmpleadoCentroDTO nuevoEmpleadoCentro = empleadoCentroService.crearEmpleadoCentro(empleadoCentroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleadoCentro);
    }

    @GetMapping("/{empleadoId}/{centroId}")
    public ResponseEntity<EmpleadoCentroDTO> obtenerEmpleadoCentro(@PathVariable Long empleadoId, @PathVariable Long centroId) {
        EmpleadoCentroDTO empleadoCentro = empleadoCentroService.obtenerEmpleadoCentro(empleadoId, centroId);
        if (empleadoCentro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoCentro);
    }

    @GetMapping
    public List<EmpleadoCentroDTO> listarEmpleadosCentro() {
        return empleadoCentroService.listarEmpleadosCentro();
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<EmpleadoCentroDTO> obtenerCentrosPorEmpleado(@PathVariable Long empleadoId) {
        return empleadoCentroService.obtenerCentrosPorEmpleado(empleadoId);
    }

    @GetMapping("/centro/{centroId}")
    public List<EmpleadoCentroDTO> obtenerEmpleadosPorCentro(@PathVariable Long centroId) {
        return empleadoCentroService.obtenerEmpleadosPorCentro(centroId);
    }

    @DeleteMapping("/{empleadoId}/{centroId}")
    public ResponseEntity<Void> eliminarEmpleadoCentro(@PathVariable Long empleadoId, @PathVariable Long centroId) {
        if (empleadoCentroService.eliminarEmpleadoCentro(empleadoId, centroId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
