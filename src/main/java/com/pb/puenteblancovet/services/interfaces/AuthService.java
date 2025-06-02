package com.pb.puenteblancovet.services.interfaces;
import com.pb.puenteblancovet.dto.request.LoginRequestDto;
import com.pb.puenteblancovet.dto.request.RegisterUserDto;
import com.pb.puenteblancovet.dto.request.RegisterVeterinarianDto;
import com.pb.puenteblancovet.dto.response.LoginResponseDto;

public interface AuthService {
    void registerClient(RegisterUserDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    void registerVeterinarian(RegisterVeterinarianDto dto);
}