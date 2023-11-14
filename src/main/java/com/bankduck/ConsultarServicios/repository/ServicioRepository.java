package com.bankduck.ConsultarServicios.repository;

import com.bankduck.ConsultarServicios.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
