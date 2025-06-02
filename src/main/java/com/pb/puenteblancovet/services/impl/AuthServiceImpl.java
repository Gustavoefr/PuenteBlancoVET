package com.puenteblanco.pb.services.impl;

import com.puenteblanco.pb.dto.request.LoginRequestDto;
import com.puenteblanco.pb.dto.request.RegisterUserDto;
import com.puenteblanco.pb.dto.request.RegisterVeterinarianDto;
import com.puenteblanco.pb.dto.response.LoginResponseDto;
import com.puenteblanco.pb.entity.*;
import com.puenteblanco.pb.repository.RoleRepository;
import com.puenteblanco.pb.repository.TipoDocumentoRepository;
import com.puenteblanco.pb.repository.VeterinarioRepository;
import com.puenteblanco.pb.repository.UserRepository;
import com.puenteblanco.pb.security.JwtUtils;
import com.puenteblanco.pb.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final RoleRepository roleRepository; // ✅ nuevo
    private final VeterinarioRepository veterinarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public void registerClient(RegisterUserDto dto) {
        if (userRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("Correo ya registrado.");
        }

        if (userRepository.existsByNumeroIdentidad(dto.getNumeroIdentidad())) {
            throw new RuntimeException("Documento ya registrado.");
        }

        TipoDocumento tipoDoc = tipoDocumentoRepository.findById(dto.getTipoDocumentoId())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado."));

        Role role = roleRepository.findByNombre("CLIENT")
                .orElseThrow(() -> new RuntimeException("Rol CLIENT no encontrado."));

        User user = User.builder()
                .nombres(dto.getNombres())
                .apellidoPaterno(dto.getApellidoPaterno())
                .apellidoMaterno(dto.getApellidoMaterno())
                .contrasena(passwordEncoder.encode(dto.getContrasena()))
                .numeroIdentidad(dto.getNumeroIdentidad())
                .sexo(dto.getSexo())
                .telefono(dto.getTelefono())
                .fechaNacimiento(dto.getFechaNacimiento())
                .correo(dto.getCorreo())
                .direccion(dto.getDireccion())
                .estado(true)
                .tipoDocumento(tipoDoc)
                .role(role)
                .build();

        userRepository.save(user);
    }

    public void registerVeterinarian(RegisterVeterinarianDto dto) {
    if (userRepository.existsByCorreo(dto.getCorreo())) {
        throw new RuntimeException("Correo ya registrado");
    }

    // Crear y guardar el usuario
    User user = User.builder()
            .nombres(dto.getNombres())
            .apellidoPaterno(dto.getApellidoPaterno())
            .apellidoMaterno(dto.getApellidoMaterno())
            .contrasena(passwordEncoder.encode(dto.getContrasena()))
            .numeroIdentidad(dto.getNumeroIdentidad())
            .sexo(dto.getSexo())
            .telefono(dto.getTelefono())
            .fechaNacimiento(dto.getFechaNacimiento())
            .correo(dto.getCorreo())
            .direccion(dto.getDireccion())
            .estado(true)
            .role(roleRepository.findByNombre("VETERINARIAN").orElseThrow())
            .tipoDocumento(tipoDocumentoRepository.findById(dto.getTipoDocumentoId()).orElseThrow())
            .build();

    userRepository.save(user);

    // Crear y guardar el veterinario
    Veterinario vet = Veterinario.builder()
            .especialidad(dto.getEspecialidad())
            .estado(true)
            .usuario(user)
            .build();

    veterinarioRepository.save(vet);
}


    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getCorreo(), dto.getContrasena())
        );

        User user = userRepository.findByCorreo(dto.getCorreo())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas."));

        String token = jwtUtils.generateToken(user);

        return LoginResponseDto.builder()
                .token(token)
                .nombreCompleto(user.getNombres() + " " + user.getApellidoPaterno())
                .rol(user.getRole().getNombre()) // ✅ corregido
                .build();
    }
}
