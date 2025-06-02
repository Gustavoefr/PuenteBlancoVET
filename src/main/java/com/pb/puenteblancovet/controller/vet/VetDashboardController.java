package com.pb.puenteblancovet.controller.vet;

import com.pb.puenteblancovet.entity.Cita;
import com.pb.puenteblancovet.entity.User;
import com.pb.puenteblancovet.entity.Veterinario;
import com.pb.puenteblancovet.repository.CitaRepository;
import com.pb.puenteblancovet.repository.UserRepository;
import com.pb.puenteblancovet.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vet")
@RequiredArgsConstructor
public class VetDashboardController {

    private final CitaRepository citaRepository;
    private final UserRepository userRepository;

    // 1. Citas del día de hoy
    @GetMapping("/today-appointments-count")
    public ResponseEntity<Integer> getTodaysAppointmentsCount() {
        Veterinario vet = getCurrentVeterinario();
        int count = citaRepository.countByVeterinarioIdAndFecha(vet.getId(), LocalDate.now());
        return ResponseEntity.ok(count);
    }

    //  2. Pacientes atendidos esta semana (estado ATENDIDA)
    @GetMapping("/week-patients-count")
public ResponseEntity<Integer> getWeeklyAttendedCount() {
    Veterinario vet = getCurrentVeterinario();

    LocalDate startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY);

    int count = citaRepository.countByVeterinarioIdAndFechaBetweenAndEstado(
        vet.getId(), startOfWeek, endOfWeek, "COMPLETADA"
    );

    return ResponseEntity.ok(count);
}


    // 3. Citas del mes actual agrupadas por día (para el calendario)
    @GetMapping("/appointments-by-month")
    public ResponseEntity<?> getAppointmentsByMonth(@RequestParam("ym") String ym) {
        Veterinario vet = getCurrentVeterinario();

        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.parse(ym);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Usa yyyy-MM");
        }

        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        List<Cita> citas = citaRepository.findByVeterinarioIdAndFechaBetween(
                vet.getId(), start, end
        );

        Map<String, String> resultado = citas.stream().collect(
                Collectors.toMap(
                        c -> String.format("%02d", c.getFecha().getDayOfMonth()),
                        Cita::getEstado,
                        (estado1, estado2) -> estado1 // si hay más de una, prioriza la primera
                )
        );

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/completed-services-summary")
public ResponseEntity<Map<String, Integer>> getCompletedServicesSummary() {
    Veterinario vet = getCurrentVeterinario();

    List<Cita> citas = citaRepository.findByVeterinarioIdAndEstado(vet.getId(), "COMPLETADA");

    Map<String, Integer> resumen = citas.stream()
        .collect(Collectors.groupingBy(
            cita -> cita.getServicio().getDescripcion(),  // asume que existe cita.getServicio()
            Collectors.summingInt(c -> 1)
        ));

    return ResponseEntity.ok(resumen);
}


    private Veterinario getCurrentVeterinario() {
        String correo = AuthUtils.getAuthenticatedEmail();
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Veterinario vet = user.getVeterinario();
        if (vet == null) throw new RuntimeException("Usuario no tiene veterinario asignado");

        return vet;
    }
}
