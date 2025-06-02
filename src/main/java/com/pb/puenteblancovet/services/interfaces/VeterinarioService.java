package com.puenteblanco.pb.services.interfaces;

import com.puenteblanco.pb.dto.response.VeterinarianListResponseDto;
import java.util.List;

public interface VeterinarioService {
    List<VeterinarianListResponseDto> getVeterinariosConDetalles();
}
