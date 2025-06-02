package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.response.ServiceResponseDto;
import com.puenteblanco.pb.entity.Servicio;
import com.puenteblanco.pb.repository.ServiceRepository;
import com.puenteblanco.pb.services.interfaces.ServiceClientService;
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
