package com.puenteblanco.pb.controller.client;

import com.puenteblanco.pb.services.interfaces.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/client/veterinarians")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @GetMapping("/{id}/horarios")
    public List<String> getSlotsByDate(
            @PathVariable Long id,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return horarioService.getAvailableTimeSlots(id, fecha);
    }
}
