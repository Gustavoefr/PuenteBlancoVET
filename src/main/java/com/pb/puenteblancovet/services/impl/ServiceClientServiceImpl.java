package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.dto.response.ServiceResponseDto;
import com.pb.puenteblancovet.entity.Servicio;
import com.pb.puenteblancovet.repository.ServiceRepository;
import com.pb.puenteblancovet.services.interfaces.ServiceClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceClientServiceImpl implements ServiceClientService {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceResponseDto> getAllActiveServices() {
        List<Servicio> servicios = serviceRepository.findByEstadoTrue();
        return servicios.stream()
                .map(servicio -> new ServiceResponseDto(servicio.getId(), servicio.getDescripcion()))
                .collect(Collectors.toList());
    }
}
