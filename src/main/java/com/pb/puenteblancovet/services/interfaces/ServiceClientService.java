package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.ServiceResponseDto;

import java.util.List;

public interface ServiceClientService {
    List<ServiceResponseDto> getAllActiveServices();
}
