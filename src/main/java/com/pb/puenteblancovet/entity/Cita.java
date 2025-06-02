package com.pb.puenteblancovet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita extends AudityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @Column(name = "precio_cobrado")
    private BigDecimal precioCobrado;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
}
