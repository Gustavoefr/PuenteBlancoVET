package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.ServiceResponseDto;

import java.util.List;

public interface ServiceClientService {
    List<ServiceResponseDto> getAllActiveServices();
}
