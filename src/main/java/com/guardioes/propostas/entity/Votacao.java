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

    @OneToOne
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId;

    @Enumerated(EnumType.STRING)
    private StatusVaga voto;

    public enum StatusVaga {
        Aprovar, Rejeitar
    }
}
