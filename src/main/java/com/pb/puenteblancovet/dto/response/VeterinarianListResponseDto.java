package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VeterinarianListResponseDto {

    private Long id;
    private String nombreCompleto;
    private String especialidad;
    private String genero;
    private String descripcion;
    private List<String> diasDisponibles; 
    private String horario; 
}
