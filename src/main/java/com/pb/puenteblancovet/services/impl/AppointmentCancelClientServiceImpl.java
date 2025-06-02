package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.response.AppointmentCancelOptionDto;
import com.puenteblanco.pb.entity.Cita;
import com.puenteblanco.pb.entity.User;
import com.puenteblanco.pb.repository.CitaRepository;
import com.puenteblanco.pb.repository.UserRepository;
import com.puenteblanco.pb.services.interfaces.AppointmentCancelClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentCancelClientServiceImpl implements AppointmentCancelClientService {

    private final CitaRepository citaRepository;
    private final UserRepository userRepository;

    @Override
    public List<AppointmentCancelOptionDto> getCancelableAppointments(Authentication auth) {
        String correo = auth.getName();
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return citaRepository.findByUsuario(user).stream()
                .filter(c -> c.getEstado().equals("PROGRAMADA"))
                .map(c -> new AppointmentCancelOptionDto(
                        c.getId(),
                        c.getFecha().toString(),
                        c.getHora().toString(),
                        c.getVeterinario().getUsuario().getNombres(),
                        c.getServicio().getDescripcion()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelAppointment(Long id, Authentication auth) {
        String correo = auth.getName();
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (!cita.getUsuario().getCorreo().equals(correo)) {
            throw new RuntimeException("No autorizado para cancelar esta cita.");
        }

        cita.setEstado("CANCELADA");
        citaRepository.save(cita);
    }
}
