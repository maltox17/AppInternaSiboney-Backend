package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.service.CentroTrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la entidad CentroTrabajo.
 * Gestiona las solicitudes HTTP para realizar operaciones CRUD.
 */
@RestController
@RequestMapping("/api/centros")
public class CentroTrabajoController {

    private final CentroTrabajoService centroTrabajoService;

    @Autowired
    public CentroTrabajoController(CentroTrabajoService centroTrabajoService) {
        this.centroTrabajoService = centroTrabajoService;
    }

    /**
     * Crea un nuevo centro de trabajo.
     * @param centroTrabajo Datos del nuevo centro de trabajo.
     * @return El centro de trabajo creado o un error 400 si los datos no son válidos.
     */
    @PostMapping
    public ResponseEntity<?> crearCentroTrabajo(@Valid @RequestBody CentroTrabajo centroTrabajo) {
        CentroTrabajo nuevoCentro = centroTrabajoService.crearCentroTrabajo(centroTrabajo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCentro);
    }

    /**
     * Obtiene un centro de trabajo por su ID.
     * @param id ID del centro de trabajo.
     * @return El centro de trabajo correspondiente al ID o un error 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCentroTrabajoPorId(@PathVariable Long id) {
        try {
            CentroTrabajo centro = centroTrabajoService.obtenerCentroTrabajoPorId(id);
            return ResponseEntity.ok(centro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Centro de trabajo no encontrado");
        }
    }

    /**
     * Lista todos los centros de trabajo.
     * @return Lista de todos los centros de trabajo.
     */
    @GetMapping
    public ResponseEntity<List<CentroTrabajo>> listarCentrosTrabajo() {
        List<CentroTrabajo> centros = centroTrabajoService.listarCentrosTrabajo();
        return ResponseEntity.ok(centros);
    }

    /**
     * Actualiza un centro de trabajo existente.
     * @param id ID del centro de trabajo a actualizar.
     * @param centroTrabajo Datos actualizados del centro de trabajo.
     * @return El centro de trabajo actualizado o un error 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCentroTrabajo(@PathVariable Long id, @Valid @RequestBody CentroTrabajo centroTrabajo) {
        CentroTrabajo centroActualizado = centroTrabajoService.actualizarCentroTrabajo(id, centroTrabajo);
    
        // Verificamos si el centro fue encontrado
        if (centroActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Centro de trabajo no encontrado");
        }
    
        // Si el centro fue encontrado y actualizado, devolvemos un 200 OK con el centro actualizado
        return ResponseEntity.ok(centroActualizado);
    }
    
    

    /**
     * Elimina un centro de trabajo por su ID.
     * @param id ID del centro de trabajo a eliminar.
     * @return Un código 204 No Content si se elimina correctamente o un error 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCentroTrabajo(@PathVariable Long id) {
        // Primero verificamos si el centro existe
        if (centroTrabajoService.obtenerCentroTrabajoPorId(id) == null) {
            // Si no existe, devolvemos un 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Si existe, procedemos a eliminarlo
        centroTrabajoService.eliminarCentroTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}
