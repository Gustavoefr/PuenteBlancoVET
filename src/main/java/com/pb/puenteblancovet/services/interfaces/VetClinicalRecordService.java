package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.AppointmentDetailResponseDto;
import com.pb.puenteblancovet.dto.request.AttendAppointmentRequestDto;

public interface VetClinicalRecordService {

    AppointmentDetailResponseDto getAppointmentDetails(Long citaId);

    void saveMedicalAttention(Long citaId, AttendAppointmentRequestDto dto);
}
