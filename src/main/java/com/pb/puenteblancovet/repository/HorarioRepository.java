package com.pb.puenteblancovet.repository;

import com.pb.puenteblancovet.entity.Horario;
import com.pb.puenteblancovet.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByVeterinario(Veterinario veterinario);

    // 🔍 NUEVO: para filtrar horarios activos por día y veterinario
    List<Horario> findByVeterinarioIdAndDiaSemanaAndEstadoTrue(Long veterinarioId, String diaSemana);
}
