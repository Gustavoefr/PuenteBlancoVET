package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.dto.dashboard.DashboardClientResponseDto;
import com.pb.puenteblancovet.entity.User;
import com.pb.puenteblancovet.repository.UserRepository;
import com.pb.puenteblancovet.services.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    @Override
    public DashboardClientResponseDto getClientDashboard(Authentication authentication) {
        String correo = authentication.getName(); 

        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String fullName = user.getNombres() + " " + user.getApellidoPaterno();

        return new DashboardClientResponseDto(
                fullName,
                "/calendar",             // ruta para ver citas
                "/payments/pending",     // ruta para ver pagos pendientes
                "/reports"               // ruta para ver reportes
        );
    }
}
