package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.dto.response.AppointmentCancelOptionDto;
import com.pb.puenteblancovet.services.interfaces.AppointmentCancelClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/cancel-appointments")
@RequiredArgsConstructor
public class AppointmentCancelClientController {

    private final AppointmentCancelClientService appointmentCancelClientService;

    @GetMapping
    public List<AppointmentCancelOptionDto> getCancelableAppointments(Authentication authentication) {
        return appointmentCancelClientService.getCancelableAppointments(authentication);
    }

    @PostMapping("/{id}/cancel")
    public void cancelAppointment(@PathVariable Long id, Authentication authentication) {
        appointmentCancelClientService.cancelAppointment(id, authentication);
    }
}
