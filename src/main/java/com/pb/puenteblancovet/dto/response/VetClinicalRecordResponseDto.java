package com.pb.puenteblancovet.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VetClinicalRecordResponseDto {
    private LocalDate fecha;
    private String veterinario;
    private String servicio;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
}
