package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.VetClinicalRecordResponseDto;
import com.pb.puenteblancovet.dto.response.VetPatientListResponseDto;

import java.util.List;

public interface VetPatientHistoryService {
    List<VetPatientListResponseDto> getAllPatientsWithHistory();
    List<VetClinicalRecordResponseDto> getClinicalHistoryByPet(Long petId);
    
}
