package com.proyecto.appInternaSiboney.controller;


import com.proyecto.appInternaSiboney.dto.CentroCreateDTO;
import com.proyecto.appInternaSiboney.dto.CentroDTO;
import com.proyecto.appInternaSiboney.service.CentroTrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroTrabajoController {

    @Autowired
    private CentroTrabajoService centroTrabajoService;

    @GetMapping
    public ResponseEntity<List<CentroDTO>> listarCentrosTrabajo() {
        List<CentroDTO> centros = centroTrabajoService.listarCentrosTrabajo();
        return ResponseEntity.ok(centros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroDTO> obtenerCentroTrabajo(@PathVariable Long id) {
        CentroDTO centro = centroTrabajoService.obtenerCentroTrabajoPorId(id);
        return ResponseEntity.ok().body(centro);
    }

    @PostMapping
    public ResponseEntity<CentroDTO> crearCentroTrabajo(@Valid @RequestBody CentroCreateDTO centroCreateDTO) {
        CentroDTO nuevoCentro = centroTrabajoService.crearCentroTrabajo(centroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCentro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroDTO> actualizarCentroTrabajo(@PathVariable Long id,
                                                             @Valid @RequestBody CentroCreateDTO centroCreateDTO) {
        CentroDTO centroActualizado = centroTrabajoService.actualizarCentroTrabajo(id, centroCreateDTO);
        return ResponseEntity.ok().body(centroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCentroTrabajo(@PathVariable Long id) {
        centroTrabajoService.eliminarCentroTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}


