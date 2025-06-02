package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.entity.Pet;
import com.pb.puenteblancovet.security.AuthUtils;
import com.pb.puenteblancovet.services.interfaces.PetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    // Mostrar formulario para registrar mascota
    @GetMapping
    public String showPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "add-pet";
    }

    // Registrar nueva mascota (formulario POST)
    @PostMapping
    public String registerPet(@ModelAttribute Pet pet) {
        String email = AuthUtils.getAuthenticatedEmail();

        if (email == null) {
            throw new RuntimeException("No authenticated user found");
        }

        pet.setOwnerEmail(email);
        petService.registerPet(pet);

        return "redirect:/dashboard";
    }

    // Eliminar lógicamente la mascota por ID
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletePetById(@PathVariable Long id) {
        try {
            petService.deleteById(id); // ahora es lógica (cambia estado a 0)
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar la mascota.");
        }
    }

    // Listar solo mascotas activas para el cliente actual
    @GetMapping("/actives")
    @ResponseBody
    public ResponseEntity<?> getActivePetsForCurrentUser() {
        String email = AuthUtils.getAuthenticatedEmail();
        if (email == null) {
            return ResponseEntity.badRequest().body("Usuario no autenticado");
        }

        List<Pet> activePets = petService.getActivePetsByOwnerEmail(email);
        return ResponseEntity.ok(activePets);
    }
}
