package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.response.VetAppointmentResponseDto;
import com.puenteblanco.pb.entity.Cita;
import com.puenteblanco.pb.entity.User;
import com.puenteblanco.pb.entity.Veterinario;
import com.puenteblanco.pb.repository.CitaRepository;
import com.puenteblanco.pb.repository.UserRepository;
import com.puenteblanco.pb.security.AuthUtils;
import com.puenteblanco.pb.services.interfaces.VetAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetAppointmentServiceImpl implements VetAppointmentService {

    private final CitaRepository citaRepository;
    private final UserRepository userRepository;

    @Override
    public List<VetAppointmentResponseDto> getAppointmentsForDate(LocalDate date) {
        String correo = AuthUtils.getAuthenticatedEmail();
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Veterinario vet = user.getVeterinario();
        if (vet == null) throw new RuntimeException("Usuario no es veterinario");

        List<Cita> citas = citaRepository.findByVeterinarioIdAndFecha(vet.getId(), date);

        return citas.stream().map(cita -> {
            String nombreCliente = cita.getUsuario().getNombres() + " " + cita.getUsuario().getApellidoPaterno();
            String nombreMascota = cita.getPet() != null ? cita.getPet().getName() : "(No registrada)";
            String nombreServicio = cita.getServicio().getDescripcion();
            String comentarios = ""; // Agregar cuando la entidad Cita tenga un campo comentarios

            return new VetAppointmentResponseDto(
                    cita.getId(),
                    cita.getHora().toString(),
                    nombreCliente,
                    nombreMascota,
                    nombreServicio,
                    comentarios,
                    cita.getEstado()
            );
        }).collect(Collectors.toList());
    }
}
