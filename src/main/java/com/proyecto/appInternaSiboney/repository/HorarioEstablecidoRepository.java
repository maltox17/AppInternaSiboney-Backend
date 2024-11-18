package com.proyecto.appInternaSiboney.repository;

import com.proyecto.appInternaSiboney.entity.HorariosEstablecidos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioEstablecidoRepository extends JpaRepository<HorariosEstablecidos, Long> {

    List<HorariosEstablecidos> findByEmpleadoId(Long empleadoId);
}
