package com.pb.puenteblancovet.controller.vet;

import com.pb.puenteblancovet.dto.response.VetClinicalRecordResponseDto;
import com.pb.puenteblancovet.dto.response.VetPatientListResponseDto;
import com.pb.puenteblancovet.services.interfaces.VetPatientHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vet/history")
@RequiredArgsConstructor
public class VetPatientHistoryController {

    private final VetPatientHistoryService vetPatientHistoryService;

    // Lista todos los pacientes que tienen al menos un historial médico
    @GetMapping
    @PreAuthorize("hasRole('VETERINARIAN')")
    public ResponseEntity<List<VetPatientListResponseDto>> getAllPatientsWithHistory() {
        List<VetPatientListResponseDto> patients = vetPatientHistoryService.getAllPatientsWithHistory();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{petId}/clinical")
    @PreAuthorize("hasRole('VETERINARIAN')")
    public ResponseEntity<List<VetClinicalRecordResponseDto>> getClinicalHistoryByPet(@PathVariable Long petId) {
        List<VetClinicalRecordResponseDto> history = vetPatientHistoryService.getClinicalHistoryByPet(petId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<List<VetClinicalRecordResponseDto>> getClinicalHistory(@PathVariable Long petId) {
        List<VetClinicalRecordResponseDto> records = vetPatientHistoryService.getClinicalHistoryByPet(petId);
        return ResponseEntity.ok(records);
    }
}
