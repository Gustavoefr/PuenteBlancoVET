package com.pb.puenteblancovet.repository;

import com.pb.puenteblancovet.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Servicio, Long> {

    // Devuelve todos los servicios activos
    List<Servicio> findByEstadoTrue();
}
