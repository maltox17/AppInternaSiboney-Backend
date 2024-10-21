package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.EmpleadoCentro;
import com.proyecto.appInternaSiboney.entity.EmpleadoCentroPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoCentroRepository extends JpaRepository<EmpleadoCentro, EmpleadoCentroPK> {
    List<EmpleadoCentro> findByEmpleadoId(Long empleadoId);
    List<EmpleadoCentro> findByCentroTrabajoId(Long centroId);
}
