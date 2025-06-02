package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VetAppointmentResponseDto {
    private Long id;
    private String hora;    
    private String cliente;    
    private String mascota;      
    private String servicio;     
    private String comentarios; 
    private String estado;      
}
