package com.pb.puenteblancovet.controller.client;

import com.pb.puenteblancovet.dto.response.ServiceResponseDto;
import com.pb.puenteblancovet.services.interfaces.ServiceClientService;
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
