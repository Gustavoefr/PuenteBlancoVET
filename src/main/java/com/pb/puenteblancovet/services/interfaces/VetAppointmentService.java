package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.VetAppointmentResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface VetAppointmentService {
    List<VetAppointmentResponseDto> getAppointmentsForDate(LocalDate date);
}
