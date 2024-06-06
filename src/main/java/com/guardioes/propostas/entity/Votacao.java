package com.guardioes.propostas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "votacao")
public class Votacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "titulo")
    private String titulo;

    @Column(name = "funcionario_cpf", nullable = false)
    private String funcionarioCpf;

    @Enumerated(EnumType.STRING)
    private StatusVaga voto;

    public enum StatusVaga {
        APROVAR, REJEITAR
    }
}
