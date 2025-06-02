package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.response.AppointmentCalendarResponseDto;
import com.puenteblanco.pb.entity.Cita;
import com.puenteblanco.pb.entity.User;
import com.puenteblanco.pb.repository.CitaRepository;
import com.puenteblanco.pb.repository.UserRepository;
import com.puenteblanco.pb.services.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final CitaRepository citaRepository;
    private final UserRepository userRepository;

    @Override
    public List<AppointmentCalendarResponseDto> getCalendarAppointments(Authentication authentication) {
        User user = userRepository.findByCorreo(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return citaRepository.findByUsuario(user).stream()
                .filter(cita -> "PROGRAMADA".equalsIgnoreCase(cita.getEstado())) // solo programadas
                .map(cita -> new AppointmentCalendarResponseDto(
                        "Cita: " + cita.getServicio().getDescripcion() +
                        " con " + cita.getVeterinario().getUsuario().getNombres(),
                        cita.getFecha().toString() + "T" + cita.getHora().toString() // ISO 8601
                ))
                .collect(Collectors.toList());
    }
}

