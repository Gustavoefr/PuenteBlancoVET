package com.puenteblanco.pb.controller.client.view;

import com.puenteblanco.pb.dto.dashboard.DashboardClientResponseDto;
import com.puenteblanco.pb.entity.Pet;
import com.puenteblanco.pb.repository.PetRepository;
import com.puenteblanco.pb.security.AuthUtils;
import com.puenteblanco.pb.services.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardViewController {

    private final DashboardService dashboardService;
    private final PetRepository petRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        DashboardClientResponseDto dto = dashboardService.getClientDashboard(authentication);
        model.addAttribute("dashboard", dto);

        String email = AuthUtils.getAuthenticatedEmail();
        List<Pet> pets = petRepository.findByOwnerEmailAndEstado(email,1);
        model.addAttribute("pets", pets);

        return "dashboard";
    }
}

