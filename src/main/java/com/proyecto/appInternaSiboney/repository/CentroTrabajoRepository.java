package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad CentroTrabajo, extiende JpaRepository para
 * proporcionar operaciones CRUD básicas.
 */
@Repository
public interface CentroTrabajoRepository extends JpaRepository<CentroTrabajo, Long> {
}
