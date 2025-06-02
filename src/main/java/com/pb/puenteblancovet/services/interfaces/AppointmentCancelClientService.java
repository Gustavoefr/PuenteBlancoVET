package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.AppointmentCancelOptionDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentCancelClientService {
    List<AppointmentCancelOptionDto> getCancelableAppointments(Authentication auth);
    void cancelAppointment(Long appointmentId, Authentication auth);
}
