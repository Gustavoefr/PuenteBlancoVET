package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.dto.response.AppointmentCalendarResponseDto;
import com.pb.puenteblancovet.services.interfaces.AppointmentService;
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
