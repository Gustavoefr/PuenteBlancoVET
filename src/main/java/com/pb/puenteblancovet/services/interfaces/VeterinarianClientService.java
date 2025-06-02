package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.VeterinarianResponseDto;

import java.util.List;

public interface VeterinarianClientService {
    List<VeterinarianResponseDto> getAllVeterinarians();
}
