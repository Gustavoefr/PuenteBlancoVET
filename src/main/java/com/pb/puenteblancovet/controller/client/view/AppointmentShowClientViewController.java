package com.pb.puenteblancovet.controller.client.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentShowClientViewController {

    @GetMapping("/appointments")
    public String showAppointmentsView(Authentication authentication, Model model) {
        // Puedes agregar lógica opcional, como pasar el nombre del usuario si deseas mostrarlo en la vista.
        model.addAttribute("fullName", authentication != null ? authentication.getName() : "Usuario");
        return "show_appointments"; // El archivo debe estar ubicado en templates/show_appointments.html
    }
}

