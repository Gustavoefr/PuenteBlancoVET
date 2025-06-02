package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VetPatientListResponseDto {
    private Long id;         // ID de la mascota
    private String name;     // Nombre de la mascota
    private String breed;    // Raza
    private String type;     // Tipo (Perro, Gato...)
    private String owner;    // Nombre del dueño
}
