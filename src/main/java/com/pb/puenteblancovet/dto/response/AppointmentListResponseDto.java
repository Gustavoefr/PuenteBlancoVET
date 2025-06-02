package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentListResponseDto {
    private String servicio;
    private String veterinario;
    private String mascota;
    private String fecha; 
    private String hora;  
    private String estado;
}
