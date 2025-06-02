package com.pb.puenteblancovet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AudityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "numero_identidad", unique = true, nullable = false)
    private String numeroIdentidad;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(unique = true, nullable = false)
    private String correo;

    private String direccion;

    @Column(nullable = false)
    private Boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumento tipoDocumento;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Veterinario veterinario;
}
