package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.dashboard.DashboardClientResponseDto;
import org.springframework.security.core.Authentication;

public interface DashboardService {
    DashboardClientResponseDto getClientDashboard(Authentication auth);
}
