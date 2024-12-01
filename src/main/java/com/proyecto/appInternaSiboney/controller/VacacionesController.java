package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.VacacionesDTO;
import com.proyecto.appInternaSiboney.dto.VacacionesNombreDTO;
import com.proyecto.appInternaSiboney.dto.VacacionesCreateDTO;
import com.proyecto.appInternaSiboney.service.VacacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/vacaciones")
public class VacacionesController {

    @Autowired
    private VacacionesService vacacionesService;

    @GetMapping
    public ResponseEntity<List<VacacionesDTO>> listarVacaciones() {
        List<VacacionesDTO> vacaciones = vacacionesService.listarVacaciones();
        return ResponseEntity.ok(vacaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacacionesDTO> obtenerVacaciones(@PathVariable Long id) {
        VacacionesDTO vacaciones = vacacionesService.obtenerVacacionesPorId(id);
        return ResponseEntity.ok().body(vacaciones);
    }

    @PostMapping
    public ResponseEntity<VacacionesDTO> crearVacaciones(@Valid @RequestBody VacacionesCreateDTO vacacionesCreateDTO) {
        VacacionesDTO nuevasVacaciones = vacacionesService.crearVacaciones(vacacionesCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevasVacaciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacacionesDTO> actualizarVacaciones(@PathVariable Long id,
                                                              @Valid @RequestBody VacacionesCreateDTO vacacionesCreateDTO) {
        VacacionesDTO vacacionesActualizadas = vacacionesService.actualizarVacaciones(id, vacacionesCreateDTO);
        return ResponseEntity.ok().body(vacacionesActualizadas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVacaciones(@PathVariable Long id) {
        vacacionesService.eliminarVacaciones(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<VacacionesDTO>> listarVacacionesPorEmpleado(@PathVariable Long empleadoId) {
        List<VacacionesDTO> vacaciones = vacacionesService.listarVacacionesPorEmpleado(empleadoId);
        return ResponseEntity.ok().body(vacaciones);
    }

    @GetMapping("/ano")
    public ResponseEntity<List<VacacionesDTO>> obtenerVacacionesPorAno(@RequestParam(required = false) Integer year) {
        List<VacacionesDTO> vacaciones = vacacionesService.obtenerVacacionesPorAno(year);
        if (vacaciones.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 si no hay datos
        }
        return ResponseEntity.ok().body(vacaciones);  // 200 OK si hay datos
    }

    @GetMapping("/empleados/nombre")
    public ResponseEntity<List<VacacionesNombreDTO>> obtenerVacacionesConNombreDeEmpleado() {
        List<VacacionesNombreDTO> vacaciones = vacacionesService.obtenerVacacionesconNombre();
        return ResponseEntity.ok(vacaciones);
    }
}
