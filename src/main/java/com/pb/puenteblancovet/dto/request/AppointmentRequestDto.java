package com.pb.puenteblancovet.dto.request;

import lombok.Data;

@Data
public class AppointmentRequestDto {
    private Long servicioId;
    private Long veterinarioId;
    private String fecha;
    private String hora;
    private Long petId;  
}
