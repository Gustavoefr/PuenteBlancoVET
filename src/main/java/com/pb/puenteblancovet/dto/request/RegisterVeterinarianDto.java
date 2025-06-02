package com.pb.puenteblancovet.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterVeterinarianDto {
    
    
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String contrasena;
    private String numeroIdentidad;
    private String sexo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String correo;
    private String direccion;
    private Long tipoDocumentoId;

    // Datos del veterinario
    private String especialidad;
}
