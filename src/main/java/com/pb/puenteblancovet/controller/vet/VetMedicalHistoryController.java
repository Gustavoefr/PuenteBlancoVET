package com.pb.puenteblancovet.controller.vet;

import com.pb.puenteblancovet.dto.request.AttendAppointmentRequestDto;
import com.pb.puenteblancovet.dto.response.AppointmentDetailResponseDto;
import com.pb.puenteblancovet.services.interfaces.VetClinicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vet/appointments")
@RequiredArgsConstructor
public class VetMedicalHistoryController {

    private final VetClinicalRecordService vetClinicalRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailResponseDto> getAppointmentDetails(@PathVariable Long id) {
        AppointmentDetailResponseDto response = vetClinicalRecordService.getAppointmentDetails(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/attend")
    public ResponseEntity<Void> attendAppointment(@PathVariable Long id,
                                                  @RequestBody AttendAppointmentRequestDto dto) {
        vetClinicalRecordService.saveMedicalAttention(id, dto);
        return ResponseEntity.ok().build();
    }
}
