package com.pb.puenteblancovet.controller.client.view;

import com.pb.puenteblancovet.dto.dashboard.DashboardClientResponseDto;
import com.pb.puenteblancovet.entity.Pet;
import com.pb.puenteblancovet.repository.PetRepository;
import com.pb.puenteblancovet.security.AuthUtils;
import com.pb.puenteblancovet.services.interfaces.DashboardService;
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

