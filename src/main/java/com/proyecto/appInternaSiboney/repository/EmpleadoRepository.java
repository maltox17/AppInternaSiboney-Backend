package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Rol;

import java.util.Optional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para gestionar entidad Empleado
 * 
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    List<Empleado> findByRol(Rol rol);
    Optional<Empleado> findByEmail(String email);
    Optional<Empleado> findByUsername(String username);

}

