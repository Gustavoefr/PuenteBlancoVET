package com.puenteblanco.pb.services.interfaces;
import com.puenteblanco.pb.dto.request.LoginRequestDto;
import com.puenteblanco.pb.dto.request.RegisterUserDto;
import com.puenteblanco.pb.dto.request.RegisterVeterinarianDto;
import com.puenteblanco.pb.dto.response.LoginResponseDto;

public interface AuthService {
    void registerClient(RegisterUserDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    void registerVeterinarian(RegisterVeterinarianDto dto);
}