package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.VetAppointmentResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface VetAppointmentService {
    List<VetAppointmentResponseDto> getAppointmentsForDate(LocalDate date);
}
