// AppointmentController.java
package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.dto.response.AppointmentListResponseDto;
import com.pb.puenteblancovet.services.interfaces.AppointmentShowClientService;
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
