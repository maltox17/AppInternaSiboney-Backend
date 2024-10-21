package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoDTO;
import com.proyecto.appInternaSiboney.service.HorarioEstablecidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horariosEstablecidos")
public class HorarioEstablecidoController {

    @Autowired
    private HorarioEstablecidoService horarioEstablecidoService;

    @PostMapping
    public ResponseEntity<HorarioEstablecidoDTO> crearHorarioEstablecido(@RequestBody HorarioEstablecidoCreateDTO horarioCreateDTO) {
        HorarioEstablecidoDTO nuevoHorario = horarioEstablecidoService.crearHorarioEstablecido(horarioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHorario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioEstablecidoDTO> obtenerHorarioEstablecido(@PathVariable Long id) {
        HorarioEstablecidoDTO horario = horarioEstablecidoService.obtenerHorarioEstablecidoPorId(id);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horario);
    }

    @GetMapping
    public List<HorarioEstablecidoDTO> listarHorariosEstablecidos() {
        return horarioEstablecidoService.listarHorariosEstablecidos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioEstablecidoDTO> actualizarHorarioEstablecido(@PathVariable Long id, @RequestBody HorarioEstablecidoDTO horarioDTO) {
        HorarioEstablecidoDTO horarioActualizado = horarioEstablecidoService.actualizarHorarioEstablecido(id, horarioDTO);
        if (horarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorarioEstablecido(@PathVariable Long id) {
        if (horarioEstablecidoService.eliminarHorarioEstablecido(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
