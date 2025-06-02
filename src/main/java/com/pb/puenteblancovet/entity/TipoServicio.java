package com.pb.puenteblancovet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_servicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoServicio extends AudityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado = true;
}
