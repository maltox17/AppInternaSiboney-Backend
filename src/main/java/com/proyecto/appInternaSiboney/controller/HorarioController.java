package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.HorarioDTO;
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
    HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioDTO> crearHorario(@Valid @RequestBody HorarioDTO horarioDTO) {
        HorarioDTO nuevoHorario = horarioService.crearHorario(horarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHorario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> obtenerHorario(@PathVariable Long id) {
        HorarioDTO horario = horarioService.obtenerHorarioPorId(id);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horario);
    }

    @GetMapping
    public List<HorarioDTO> listarHorarios() {
        return horarioService.listarHorarios();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> actualizarHorario(@PathVariable Long id, @Valid @RequestBody HorarioDTO horarioDTO) {
        HorarioDTO horarioActualizado = horarioService.actualizarHorario(id, horarioDTO);
        if (horarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {
        if (!horarioService.eliminarHorario(id)) {
            return ResponseEntity.notFound().build();
        }
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

        /**
     * Endpoint para obtener horarios filtrados por semana de inicio, centro de trabajo y turno.
     *
     * @param semana Número de la semana en el año.
     * @param centroTrabajoId ID del centro de trabajo.
     * @param turno Turno del horario (MAÑANA, TARDE, NOCHE).
     * @return Lista de horarios que coinciden con los criterios.
     */
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
    

    

    
}
