package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.HorasExtrasDeuda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorasExtrasDeudaRepository extends JpaRepository<HorasExtrasDeuda, Long> {
    List<HorasExtrasDeuda> findByEmpleado_Id(Long empleadoId);
}
