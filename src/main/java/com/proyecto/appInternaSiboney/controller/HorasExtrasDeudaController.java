package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaCreateDTO;
import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaDTO;
import com.proyecto.appInternaSiboney.dto.HorasExtrasDeudaEditDTO;
import com.proyecto.appInternaSiboney.service.HorasExtrasDeudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/horasExtrasDeuda")
public class HorasExtrasDeudaController {

    @Autowired
    private HorasExtrasDeudaService horasExtrasDeudaService;

    @PostMapping
    public ResponseEntity<HorasExtrasDeudaDTO> crearHorasExtrasDeuda(@Valid @RequestBody HorasExtrasDeudaCreateDTO dto) {
        HorasExtrasDeudaDTO nuevaHorasExtrasDeuda = horasExtrasDeudaService.crearHorasExtrasDeuda(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHorasExtrasDeuda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorasExtrasDeudaDTO> obtenerHorasExtrasDeudaPorId(@PathVariable Long id) {
        HorasExtrasDeudaDTO horasExtrasDeuda = horasExtrasDeudaService.obtenerPorId(id);
        return ResponseEntity.ok().body(horasExtrasDeuda);
    }

    @GetMapping
    public ResponseEntity<List<HorasExtrasDeudaDTO>> listarHorasExtrasDeuda() {
        List<HorasExtrasDeudaDTO> lista = horasExtrasDeudaService.listarHorasExtrasDeuda();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorasExtrasDeudaDTO> actualizarHorasExtrasDeuda(@PathVariable Long id,
                                                                         @Valid @RequestBody HorasExtrasDeudaEditDTO dto) {
        HorasExtrasDeudaDTO actualizada = horasExtrasDeudaService.actualizarHorasExtrasDeuda(id, dto);
        return ResponseEntity.ok().body(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorasExtrasDeuda(@PathVariable Long id) {
        horasExtrasDeudaService.eliminarHorasExtrasDeuda(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<HorasExtrasDeudaDTO>> buscarPorEmpleadoId(@PathVariable Long empleadoId) {
        List<HorasExtrasDeudaDTO> lista = horasExtrasDeudaService.buscarPorEmpleadoId(empleadoId);
        return ResponseEntity.ok().body(lista);
    }
}
