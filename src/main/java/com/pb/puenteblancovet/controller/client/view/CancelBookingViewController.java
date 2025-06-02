package com.pb.puenteblancovet.controller.client.view;
import com.pb.puenteblancovet.dto.dashboard.DashboardClientResponseDto;
import com.pb.puenteblancovet.services.interfaces.DashboardService;
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
