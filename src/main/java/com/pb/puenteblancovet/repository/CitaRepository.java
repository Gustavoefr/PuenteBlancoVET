package com.pb.puenteblancovet.repository;

import com.pb.puenteblancovet.entity.Cita;
import com.pb.puenteblancovet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByUsuario(User usuario);

    List<Cita> findByVeterinarioIdAndFecha(Long veterinarioId, LocalDate fecha);

    List<Cita> findByVeterinarioIdAndFechaBetween(Long veterinarioId, LocalDate desde, LocalDate hasta);

    List<Cita> findByVeterinarioIdAndEstado(Long vetId, String estado);

    List<Cita> findByVeterinarioIdAndFechaBetweenAndEstado(Long vetId, LocalDate desde, LocalDate hasta, String estado);

    int countByVeterinarioIdAndFecha(Long vetId, LocalDate fecha);

    int countByVeterinarioIdAndFechaBetweenAndEstado(Long vetId, LocalDate desde, LocalDate hasta, String estado);


}
