// AppointmentController.java
package com.puenteblanco.pb.controller.client;

import com.puenteblanco.pb.dto.response.AppointmentCalendarResponseDto;
import com.puenteblanco.pb.services.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/appointments")
@RequiredArgsConstructor
public class CalendarController {

    private final AppointmentService appointmentService;

    @GetMapping("/calendar")
    public List<AppointmentCalendarResponseDto> getCalendarAppointments(Authentication authentication) {
        return appointmentService.getCalendarAppointments(authentication);
    }
}
