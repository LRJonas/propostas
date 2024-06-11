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
@Table(name= "com/guardioes/propostas")
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200, unique = true)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 11)
    private String funcionarioCpf;

    @Column(nullable = false)
    private int aprovar = 0;

    @Column(nullable = false)
    private int rejeitar = 0;

    @Column(nullable = false)
    private long tempo = 1;

    @Column(nullable = false)
    private boolean ativo = false;
}