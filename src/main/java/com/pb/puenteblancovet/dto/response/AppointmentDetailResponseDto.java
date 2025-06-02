package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDetailResponseDto {

    private Long citaId;

    private String hora;

    private String fecha;

    private String nombreCliente;

    private String emailCliente;

    private String nombreMascota;

    private String tipoMascota;

    private String razaMascota;

    private Integer edadMascota;

    private String servicio;
}
