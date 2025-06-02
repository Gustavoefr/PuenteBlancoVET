package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.VeterinarianResponseDto;

import java.util.List;

public interface VeterinarianClientService {
    List<VeterinarianResponseDto> getAllVeterinarians();
}
