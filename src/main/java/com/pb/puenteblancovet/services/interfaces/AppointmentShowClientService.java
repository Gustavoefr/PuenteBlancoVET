package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.AppointmentListResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentShowClientService {
    List<AppointmentListResponseDto> getAppointmentsByClient(Authentication auth);
}
