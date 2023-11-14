package com.bankduck.ConsultarServicios.repository;

import com.bankduck.ConsultarServicios.entities.RequisitosServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitosServicioRepository extends JpaRepository<RequisitosServicio, Long> {
    List<RequisitosServicio> findByServicioId(long servicioId);
}