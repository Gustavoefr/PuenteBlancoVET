package com.pb.puenteblancovet.repository;

import com.pb.puenteblancovet.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    // Devuelve veterinarios con estado activo (true)
    List<Veterinario> findByEstadoTrue();
}
