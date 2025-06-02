package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentCancelOptionDto {
    private Long id;
    private String fecha;
    private String hora;
    private String veterinario;
    private String servicio;
}
