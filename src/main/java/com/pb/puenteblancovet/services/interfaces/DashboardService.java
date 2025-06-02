package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.dashboard.DashboardClientResponseDto;
import org.springframework.security.core.Authentication;

public interface DashboardService {
    DashboardClientResponseDto getClientDashboard(Authentication auth);
}
