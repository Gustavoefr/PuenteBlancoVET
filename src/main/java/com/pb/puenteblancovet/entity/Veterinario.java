package com.pb.puenteblancovet.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "veterinario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veterinario extends AudityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private Boolean estado = true;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Horario> horarios;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
}

