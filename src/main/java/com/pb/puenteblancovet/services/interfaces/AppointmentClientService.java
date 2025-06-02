package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.request.AppointmentRequestDto;
import org.springframework.security.core.Authentication;

public interface AppointmentClientService {
    void bookAppointment(Authentication auth, AppointmentRequestDto dto);
}
