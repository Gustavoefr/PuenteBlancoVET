package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.AppointmentDetailResponseDto;
import com.puenteblanco.pb.dto.request.AttendAppointmentRequestDto;

public interface VetClinicalRecordService {

    AppointmentDetailResponseDto getAppointmentDetails(Long citaId);

    void saveMedicalAttention(Long citaId, AttendAppointmentRequestDto dto);
}
