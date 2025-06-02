package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.request.AppointmentRequestDto;
import org.springframework.security.core.Authentication;

public interface AppointmentClientService {
    void bookAppointment(Authentication auth, AppointmentRequestDto dto);
}
