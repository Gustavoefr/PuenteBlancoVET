package com.pb.puenteblancovet.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String contrasena;
}