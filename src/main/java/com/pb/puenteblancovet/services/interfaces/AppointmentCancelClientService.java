package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.AppointmentCancelOptionDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentCancelClientService {
    List<AppointmentCancelOptionDto> getCancelableAppointments(Authentication auth);
    void cancelAppointment(Long appointmentId, Authentication auth);
}
