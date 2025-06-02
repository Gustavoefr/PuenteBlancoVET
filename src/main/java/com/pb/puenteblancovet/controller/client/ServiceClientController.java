package com.puenteblanco.pb.controller.client;

import com.puenteblanco.pb.dto.response.ServiceResponseDto;
import com.puenteblanco.pb.services.interfaces.ServiceClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/services")
@RequiredArgsConstructor
public class ServiceClientController {

    private final ServiceClientService serviceClientService;

    @GetMapping
    public List<ServiceResponseDto> getAllActiveServices() {
        return serviceClientService.getAllActiveServices();
    }
}
