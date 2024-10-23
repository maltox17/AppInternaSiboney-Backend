package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorarioEstablecidoDTO;
import com.proyecto.appInternaSiboney.service.HorarioEstablecidoService;

import jakarta.validation.Valid;

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

    @GetMapping
    public ResponseEntity<List<HorarioEstablecidoDTO>> listarHorariosEstablecidos() {
        List<HorarioEstablecidoDTO> horarios = horarioEstablecidoService.listarHorariosEstablecidos();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioEstablecidoDTO> obtenerHorarioEstablecido(@PathVariable Long id) {
        HorarioEstablecidoDTO horario = horarioEstablecidoService.obtenerHorarioEstablecidoPorId(id);
        return ResponseEntity.ok().body(horario);
    }

    @PostMapping
    public ResponseEntity<HorarioEstablecidoDTO> crearHorarioEstablecido(@Valid @RequestBody HorarioEstablecidoCreateDTO horarioCreateDTO) {
        HorarioEstablecidoDTO nuevoHorario = horarioEstablecidoService.crearHorarioEstablecido(horarioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHorario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioEstablecidoDTO> actualizarHorarioEstablecido(@PathVariable Long id, @Valid @RequestBody HorarioEstablecidoCreateDTO horarioDTO) {
        HorarioEstablecidoDTO horarioActualizado = horarioEstablecidoService.actualizarHorarioEstablecido(id, horarioDTO);
        return ResponseEntity.ok().body(horarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorarioEstablecido(@PathVariable Long id) {
        horarioEstablecidoService.eliminarHorarioEstablecido(id);
        return ResponseEntity.noContent().build();
    }
}

