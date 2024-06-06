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

    @Column (name = "titulo")
    private String titulo;

  @Column (name = "ID_PROPOSTA")
    private Long idProposta;

    @Column(name = "funcionario_cpf", nullable = false)
    private String funcionarioCpf;

    @Enumerated(EnumType.STRING)
    private StatusVoto voto;

    public enum StatusVoto {
        APROVAR, REJEITAR
    }
}
