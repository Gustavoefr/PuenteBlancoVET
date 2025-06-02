// AppointmentController.java
package com.puenteblanco.pb.controller.client;

import com.puenteblanco.pb.dto.response.AppointmentListResponseDto;
import com.puenteblanco.pb.services.interfaces.AppointmentShowClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentShowClientService appointmentShowClientService;

    @GetMapping
    public List<AppointmentListResponseDto> getClientAppointments(Authentication auth) {
        return appointmentShowClientService.getAppointmentsByClient(auth);
    }
}
