package com.pb.puenteblancovet.controller.vet.view;

import com.pb.puenteblancovet.dto.request.AttendAppointmentRequestDto;
import com.pb.puenteblancovet.dto.response.AppointmentDetailResponseDto;
import com.pb.puenteblancovet.services.interfaces.VetClinicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vet/appointments")
@RequiredArgsConstructor
public class VetAttendAppointmentViewController {

    private final VetClinicalRecordService vetClinicalRecordService;

    // Renderizar la vista con los datos del paciente y cita
    @GetMapping("/{id}/attend")
    public String showAttendAppointmentForm(@PathVariable Long id, Model model) {
        AppointmentDetailResponseDto appointment = vetClinicalRecordService.getAppointmentDetails(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("clinicalRecord", new AttendAppointmentRequestDto());
        return "attend_appointment";
    }

    // Procesar el formulario enviado
    @PostMapping("/{id}/attend")
    public String submitAttendAppointmentForm(@PathVariable Long id,
                                              @ModelAttribute("clinicalRecord") AttendAppointmentRequestDto dto) {
        vetClinicalRecordService.saveMedicalAttention(id, dto);
        return "redirect:/vet/appointments"; // redirige a la lista de citas
    }
}
