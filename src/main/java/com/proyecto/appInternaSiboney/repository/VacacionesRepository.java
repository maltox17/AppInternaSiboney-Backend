package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad Vacaciones
 */
public interface VacacionesRepository extends JpaRepository<Vacaciones, Long> {
    List<Vacaciones> findByEmpleadoId(Long empleadoId);

    //buscar vacaciones por año
    @Query("SELECT v FROM Vacaciones v WHERE YEAR(v.fechaInicio) = :year")
    List<Vacaciones> findByAno(@Param("year") int year);

        //consulta para obtener vacaciones junto con el nombre del empleado
        @Query("SELECT v FROM Vacaciones v JOIN v.empleado e")
        List<Vacaciones> findWithEmpleadoName();
}
