package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.entity.Pet;
import com.pb.puenteblancovet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/pets")
@RequiredArgsConstructor
public class PetClientController {

    private final PetRepository petRepository;

    @GetMapping
    public ResponseEntity<List<Pet>> getPets(Authentication auth) {
        String email = auth.getName(); // Extraído del token JWT
        System.out.println("Buscando mascotas para: " + email);

        List<Pet> pets = petRepository.findByOwnerEmail(email);
        return ResponseEntity.ok(pets);
    }
}
