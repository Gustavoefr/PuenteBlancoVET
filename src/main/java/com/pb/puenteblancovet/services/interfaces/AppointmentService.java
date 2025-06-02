package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.AppointmentCalendarResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentService {
    List<AppointmentCalendarResponseDto> getCalendarAppointments(Authentication authentication);
}
