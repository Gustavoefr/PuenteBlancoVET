package com.pb.puenteblancovet.security;

import com.pb.puenteblancovet.entity.User;
import com.pb.puenteblancovet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getNombre());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getCorreo())
                .password(user.getContrasena())
                .authorities(Collections.singletonList(authority))
                .build();
    }
}
