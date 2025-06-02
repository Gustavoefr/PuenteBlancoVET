package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.AppointmentListResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentShowClientService {
    List<AppointmentListResponseDto> getAppointmentsByClient(Authentication auth);
}
