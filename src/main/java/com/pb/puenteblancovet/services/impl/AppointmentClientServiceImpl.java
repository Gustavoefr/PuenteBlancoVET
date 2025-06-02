package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.request.AppointmentRequestDto;
import com.puenteblanco.pb.entity.Cita;
import com.puenteblanco.pb.entity.Servicio;
import com.puenteblanco.pb.entity.User;
import com.puenteblanco.pb.entity.Veterinario;
import com.puenteblanco.pb.entity.Pet;
import com.puenteblanco.pb.repository.PetRepository;
import com.puenteblanco.pb.repository.CitaRepository;
import com.puenteblanco.pb.repository.ServiceRepository;
import com.puenteblanco.pb.repository.UserRepository;
import com.puenteblanco.pb.repository.VeterinarioRepository;
import com.puenteblanco.pb.services.interfaces.AppointmentClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AppointmentClientServiceImpl implements AppointmentClientService {

    private final CitaRepository citaRepository;
    private final ServiceRepository servicioRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional
    public void bookAppointment(Authentication auth, AppointmentRequestDto dto) {
        String correo = auth.getName();
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Servicio servicio = servicioRepository.findById(dto.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Veterinario veterinario = veterinarioRepository.findById(dto.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Cita cita = Cita.builder()
                .usuario(user)
                .servicio(servicio)
                .veterinario(veterinario)
                .pet(pet)
                .fecha(LocalDate.parse(dto.getFecha()))
                .hora(LocalTime.parse(dto.getHora()))
                .precioCobrado(servicio.getPrecioBase())
                .estado("PROGRAMADA")
                .build();

        citaRepository.save(cita);
    }
}
