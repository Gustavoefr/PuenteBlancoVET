package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.VetClinicalRecordResponseDto;
import com.puenteblanco.pb.dto.response.VetPatientListResponseDto;

import java.util.List;

public interface VetPatientHistoryService {
    List<VetPatientListResponseDto> getAllPatientsWithHistory();
    List<VetClinicalRecordResponseDto> getClinicalHistoryByPet(Long petId);
    
}
