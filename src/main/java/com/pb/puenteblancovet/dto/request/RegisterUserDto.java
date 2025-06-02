package com.pb.puenteblancovet.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidoPaterno;

    @NotBlank
    private String apellidoMaterno;

    @NotBlank
    private String contrasena;

    @NotBlank
    private String numeroIdentidad;

    @NotBlank
    private String sexo;

    @NotBlank
    private String telefono;

    @NotNull
    private LocalDate fechaNacimiento;

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String direccion;

    @NotNull
    private Long tipoDocumentoId;
}