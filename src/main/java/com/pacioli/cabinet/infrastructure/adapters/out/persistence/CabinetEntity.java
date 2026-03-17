package com.pacioli.cabinet.infrastructure.adapters.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cabinets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CabinetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private String phone;

    @Column(unique = true, length = 15)
    private String ice;

    private String ville;
}