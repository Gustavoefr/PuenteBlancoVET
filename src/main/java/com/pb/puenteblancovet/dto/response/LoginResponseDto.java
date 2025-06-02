package com.pb.puenteblancovet.dto.response;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private String token;
    private String nombreCompleto;
    private String rol;
}