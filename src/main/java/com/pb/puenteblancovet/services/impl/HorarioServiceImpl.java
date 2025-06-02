package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.entity.Cita;
import com.pb.puenteblancovet.entity.Horario;
import com.pb.puenteblancovet.repository.CitaRepository;
import com.pb.puenteblancovet.repository.HorarioRepository;
import com.pb.puenteblancovet.services.interfaces.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final CitaRepository citaRepository;

    @Override
    public List<String> getAvailableTimeSlots(Long vetId, LocalDate fecha) {
        String diaSemana = capitalize(fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es")));

        // 1. Horarios activos para ese día y veterinario
        List<Horario> horarios = horarioRepository.findByVeterinarioIdAndDiaSemanaAndEstadoTrue(vetId, diaSemana);

        // 2. Obtener las horas ocupadas en CITA
        List<LocalTime> horasOcupadas = citaRepository.findByVeterinarioIdAndFecha(vetId, fecha)
                .stream()
                .map(Cita::getHora)
                .toList();

        // 3. Generar slots disponibles
        List<String> slots = new ArrayList<>();
        for (Horario horario : horarios) {
            LocalTime start = horario.getHoraComienzo();
            while (start.isBefore(horario.getHoraFin())) {
                if (!horasOcupadas.contains(start)) {
                    slots.add(start.toString()); // formato "12:00"
                }
                start = start.plusMinutes(20);
            }
        }

        return slots;
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
