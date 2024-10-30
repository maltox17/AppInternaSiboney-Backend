package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.HorarioCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioDTO;
import com.proyecto.appInternaSiboney.dto.HorarioNombresDTO;
import com.proyecto.appInternaSiboney.entity.Turno;
import com.proyecto.appInternaSiboney.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los horarios.
 */
@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioDTO> crearHorario(@Valid @RequestBody HorarioCreateDTO horarioDTO) {
        HorarioDTO nuevoHorario = horarioService.crearHorario(horarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHorario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> obtenerHorario(@PathVariable Long id) {
        HorarioDTO horario = horarioService.obtenerHorarioPorId(id);
        return ResponseEntity.ok().body(horario);
    }

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> listarHorarios() {
        List<HorarioDTO> horarios = horarioService.listarHorarios();
        return ResponseEntity.ok(horarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> actualizarHorario(@PathVariable Long id, @Valid @RequestBody HorarioCreateDTO horarioDTO) {
        HorarioDTO horarioActualizado = horarioService.actualizarHorario(id, horarioDTO);
        return ResponseEntity.ok().body(horarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {
        horarioService.eliminarHorario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<HorarioDTO>> listarHorariosPorEmpleado(@PathVariable Long empleadoId) {
        List<HorarioDTO> horarios = horarioService.listarHorariosPorEmpleado(empleadoId);
        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/filtro/{centroTrabajoId}/{semana}/{turno}")
    public ResponseEntity<List<HorarioDTO>> listarHorariosPorSemanaCentroYTurno(
            @PathVariable int semana,
            @PathVariable Long centroTrabajoId,
            @PathVariable Turno turno) {

        List<HorarioDTO> horarios = horarioService.listarHorariosPorSemanaCentroYTurno(semana, centroTrabajoId, turno);
        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/nombres")
    public ResponseEntity<List<HorarioNombresDTO>> obtenerHorariosConNombres() {
        List<HorarioNombresDTO> horarios = horarioService.obtenerHorariosConNombres();
        return ResponseEntity.ok(horarios);
    }
}

