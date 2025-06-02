package com.pb.puenteblancovet.services.interfaces;

import com.pb.puenteblancovet.dto.response.VeterinarianListResponseDto;
import java.util.List;

public interface VeterinarioService {
    List<VeterinarianListResponseDto> getVeterinariosConDetalles();
}
