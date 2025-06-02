package com.puenteblanco.pb.controller.client.view;

import com.puenteblanco.pb.dto.dashboard.DashboardClientResponseDto;
import com.puenteblanco.pb.services.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarViewController {

    private final DashboardService dashboardService;

    @GetMapping
    public String showCalendar(Model model, Authentication authentication) {
        DashboardClientResponseDto dashboard = dashboardService.getClientDashboard(authentication);
        model.addAttribute("dashboard", dashboard);
        return "calendar"; // Renderiza calendar.html desde /templates
    }
}
