package com.puenteblanco.pb.controller.client.view;
import com.puenteblanco.pb.dto.dashboard.DashboardClientResponseDto;
import com.puenteblanco.pb.services.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CancelBookingViewController {

    private final DashboardService dashboardService;

    @GetMapping("/cancel-appointment")
    public String showCancelPage(Authentication authentication, Model model) {
        DashboardClientResponseDto dashboard = dashboardService.getClientDashboard(authentication);
        model.addAttribute("dashboard", dashboard);
        return "cancel_booking"; // nombre del archivo HTML sin extensión .html
    }
}
