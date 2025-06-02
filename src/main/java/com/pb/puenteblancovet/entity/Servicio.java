package com.pb.puenteblancovet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "servicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servicio extends AudityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "precio_base", nullable = false)
    private BigDecimal precioBase;

    @Column(nullable = false)
    private Boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;
}
