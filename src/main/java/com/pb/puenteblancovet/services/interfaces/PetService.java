package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.entity.Pet;
import com.pb.puenteblancovet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public Pet registerPet(Pet pet) {
        String newPetId = generateNextPetId();
        pet.setPetId(newPetId);
        pet.setEstado(1); // Activar mascota al registrar
        return petRepository.save(pet);
    }

    private String generateNextPetId() {
        Optional<Pet> lastPet = petRepository.findTopByOrderByPetIdDesc();
        int nextNumber = 1;

        if (lastPet.isPresent()) {
            String lastId = lastPet.get().getPetId(); // Ej: PET-0023
            if (lastId != null && lastId.length() >= 8 && lastId.startsWith("PET-")) {
                try {
                    String numberPart = lastId.substring(4); // Extrae "0023"
                    nextNumber = Integer.parseInt(numberPart) + 1;
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Error al parsear el número de petId: " + lastId);
                    nextNumber = 1;
                }
            } else {
                System.out.println("⚠️ petId inválido detectado: " + lastId);
            }
        }

        return String.format("PET-%04d", nextNumber); // Formato: PET-0024
    }

    // Eliminación lógica
    public void deleteById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
        pet.setEstado(0); // Inactivar mascota
        petRepository.save(pet);
    }

    // Listar mascotas activas por email del dueño
    public List<Pet> getActivePetsByOwnerEmail(String email) {
        return petRepository.findByOwnerEmailAndEstado(email, 1);
    }
}
