package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.AppointmentCalendarResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentService {
    List<AppointmentCalendarResponseDto> getCalendarAppointments(Authentication authentication);
}
