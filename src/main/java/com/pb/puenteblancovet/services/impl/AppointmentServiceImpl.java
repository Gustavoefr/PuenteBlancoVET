package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.dto.response.AppointmentCalendarResponseDto;
import com.pb.puenteblancovet.entity.Cita;
import com.pb.puenteblancovet.entity.User;
import com.pb.puenteblancovet.repository.CitaRepository;
import com.pb.puenteblancovet.repository.UserRepository;
import com.pb.puenteblancovet.services.interfaces.AppointmentService;
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

