package com.pb.puenteblancovet.controller.vet;

import com.pb.puenteblancovet.dto.response.VetAppointmentResponseDto;
import com.pb.puenteblancovet.services.interfaces.VetAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vet")
@RequiredArgsConstructor
public class VetAppointmentController {

    private final VetAppointmentService vetAppointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<VetAppointmentResponseDto>> getAppointmentsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<VetAppointmentResponseDto> citas = vetAppointmentService.getAppointmentsForDate(date);
        return ResponseEntity.ok(citas);
    }
}
