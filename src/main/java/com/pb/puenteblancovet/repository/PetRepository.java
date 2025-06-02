package com.pb.puenteblancovet.repository;

import com.pb.puenteblancovet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsByPetId(String petId);
    Optional<Pet> findTopByOrderByPetIdDesc(); // Para obtener el último petId registrado
    List<Pet> findByOwnerEmail(String email);
    List<Pet> findByOwnerEmailAndEstado(String email, Integer estado);
    void deleteById(Long Id);

}
