package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.response.VeterinarianResponseDto;
import com.puenteblanco.pb.entity.Veterinario;
import com.puenteblanco.pb.repository.VeterinarioRepository;
import com.puenteblanco.pb.services.interfaces.VeterinarianClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeterinarianClientServiceImpl implements VeterinarianClientService {

    private final VeterinarioRepository veterinarioRepository;

    @Override
    public List<VeterinarianResponseDto> getAllVeterinarians() {
        List<Veterinario> veterinarios = veterinarioRepository.findByEstadoTrue();

        return veterinarios.stream()
                .map(vet -> new VeterinarianResponseDto(
                        vet.getId(),
                        (vet.getUsuario() != null)
                            ? vet.getUsuario().getNombres() + " " + vet.getUsuario().getApellidoPaterno()
                            : "Usuario no asignado",
                        vet.getEspecialidad() != null ? vet.getEspecialidad() : "Sin especialidad"
                ))
                .collect(Collectors.toList());
    }
}
