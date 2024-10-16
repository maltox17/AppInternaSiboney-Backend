package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.Horario;
import com.proyecto.appInternaSiboney.entity.Turno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByEmpleadoId(Long empleadoId);  // Para listar por empleado
    
    @Query("SELECT h FROM Horario h WHERE h.centroTrabajo.id = :centroTrabajoId AND FUNCTION('week', h.fecha) = :semana AND h.turno = :turno")
    List<Horario> findBySemanaCentroYTurno(@Param("semana") int semana,
                                           @Param("centroTrabajoId") Long centroTrabajoId,
                                           @Param("turno") Turno turno);
}

