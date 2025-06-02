package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.dto.response.VeterinarianResponseDto;
import com.pb.puenteblancovet.services.interfaces.VeterinarianClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/veterinarians")
@RequiredArgsConstructor
public class VeterinarianClientController {

    private final VeterinarianClientService veterinarianClientService;

    @GetMapping
    public ResponseEntity<List<VeterinarianResponseDto>> getAllVeterinarians() {
        List<VeterinarianResponseDto> veterinarios = veterinarianClientService.getAllVeterinarians();
        return ResponseEntity.ok(veterinarios);
    }
}
