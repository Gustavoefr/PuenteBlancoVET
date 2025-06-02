package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.response.AppointmentListResponseDto;
import com.puenteblanco.pb.entity.Cita;
import com.puenteblanco.pb.entity.User;
import com.puenteblanco.pb.repository.CitaRepository;
import com.puenteblanco.pb.repository.UserRepository;
import com.puenteblanco.pb.services.interfaces.AppointmentShowClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentShowClientServiceImpl implements AppointmentShowClientService {

    private final CitaRepository citaRepository;
    private final UserRepository userRepository;

    @Override
    public List<AppointmentListResponseDto> getAppointmentsByClient(Authentication auth) {
        String correo = auth.getName();
        User usuario = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Cita> citas = citaRepository.findByUsuario(usuario);

        return citas.stream().map(cita -> new AppointmentListResponseDto(
                cita.getServicio().getDescripcion(),
                cita.getPet().getName(),
                cita.getVeterinario().getUsuario().getNombres() + " " + cita.getVeterinario().getUsuario().getApellidoPaterno(),
                cita.getFecha().toString(),
                cita.getHora().toString(),
                cita.getEstado()
        )).collect(Collectors.toList());
    }
}
