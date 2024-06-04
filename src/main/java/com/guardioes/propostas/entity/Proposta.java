package com.guardioes.propostas.entity;

import com.guardioes.propostas.client.funcionarios.Funcionario;
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
@Table(name= "propostas")
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column(name = "funcionario_id")
    private Long funcionarioId;

    @Column
    private Integer aprovar;

    @Column
    private Integer rejeitar;

    @Column
    private boolean ativo = false;
}

