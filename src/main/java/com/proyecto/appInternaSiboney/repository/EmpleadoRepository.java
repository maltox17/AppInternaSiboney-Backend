package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Rol;

import java.util.Optional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad Empleado.
 * Utiliza JPA para interactuar con la base de datos.
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    List<Empleado> findByRol(Rol rol);
    Optional<Empleado> findByEmail(String email);
}

