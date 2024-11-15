package com.proyecto.appInternaSiboney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.appInternaSiboney.dto.EmpleadoCreateDTO;
import com.proyecto.appInternaSiboney.dto.EmpleadoDTO;
import com.proyecto.appInternaSiboney.service.EmpleadoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  
  
    @Autowired
    EmpleadoService empleadoService;

  @GetMapping("/all")
  public String showContentForAll() {
    return "Contenido público";
  }

  @GetMapping("/camarero")
  public String showContentForUsers() {
    return "Contenido para usuarios";
  }

  @GetMapping("/encargado")
  public String showContentForManager() {
    return "Contenido para usuarios de tipo Manager";
  }

  @GetMapping("/jefe")
  public String showContentForAdmins() {
    return "Contenido para administradores";
  }

  @PreAuthorize("hasRole('ENCARGADO')")
  @PostMapping("/echo")
  public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoCreateDTO empleadoCreateDTO) {
        EmpleadoDTO nuevoEmpleado = empleadoService.crearEmpleado(empleadoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

}
