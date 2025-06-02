package com.pb.puenteblancovet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(unique = true, nullable = false)
    private String petId; // ID incremental como PET-0001, PET-0002...

    @Column(nullable = false)
    private String name;

    private String type;    
    private String breed;
    private Integer age;
    private String sex;
    private Double weight;

    @Column(nullable = false)
    private String ownerEmail; 

    @Column(nullable = false)
    private Integer estado = 1; 
}
