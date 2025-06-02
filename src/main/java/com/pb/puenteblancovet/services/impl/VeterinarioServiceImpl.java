package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.dto.response.VeterinarianListResponseDto;
import com.pb.puenteblancovet.entity.Horario;
import com.pb.puenteblancovet.entity.Veterinario;
import com.pb.puenteblancovet.repository.VeterinarioRepository;
import com.pb.puenteblancovet.services.interfaces.VeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    @Override
    public List<VeterinarianListResponseDto> getVeterinariosConDetalles() {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();

        return veterinarios.stream().map(vet -> {
            String nombreCompleto = vet.getUsuario().getNombres() + " " + vet.getUsuario().getApellidoPaterno();
            String genero = mapearGenero(vet.getUsuario().getSexo());

            List<String> diasDisponibles = vet.getHorarios().stream()
                    .map(h -> capitalize(h.getDiaSemana()))
                    .distinct()
                    .collect(Collectors.toList());

            String horario = vet.getHorarios().stream()
                    .map(h -> h.getHoraComienzo() + " - " + h.getHoraFin())
                    .findFirst() // asume un rango representativo
                    .orElse("No definido");

            return new VeterinarianListResponseDto(
                    vet.getId(),
                    nombreCompleto,
                    vet.getEspecialidad(),
                    genero,
                    generarDescripcionPorEspecialidad(vet.getEspecialidad()),
                    diasDisponibles,
                    horario
            );
        }).collect(Collectors.toList());
    }

    private String mapearGenero(String sexo) {
        return switch (sexo) {
            case "F" -> "Female";
            case "M" -> "Male";
            default -> "Other";
        };
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase(Locale.ROOT) + input.substring(1).toLowerCase(Locale.ROOT);
    }

    private String generarDescripcionPorEspecialidad(String especialidad) {
        return switch (especialidad.toLowerCase()) {
            case "general" -> "Dr. has experience in general pet care and vaccinations.";
            case "surgery" -> "Expert in complex surgeries with over 1000 procedures.";
            case "dermatology" -> "Specialist in pet skin and allergy conditions.";
            default -> "Veterinarian with extensive experience.";
        };
    }
}
