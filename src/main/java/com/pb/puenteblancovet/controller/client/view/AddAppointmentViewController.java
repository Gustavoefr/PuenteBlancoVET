package com.puenteblanco.pb.controller.client.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddAppointmentViewController {

    @GetMapping("/book-appointment")
    public String renderBookAppointmentPage() {
        return "book_appointment"; // Thymeleaf busca: templates/book_appointment.html
    }
}
