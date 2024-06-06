package com.guardioes.propostas.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropostaResponseDto {

    @JsonProperty("Titulo da Proposta")
    private String titulo;

    @JsonProperty("Descricao da Proposta")
    private String descricao;

    @JsonProperty("Sugestão de")
    private String nome;

    @JsonProperty("CPF do Funcionario")
    private String funcionarioCpf;

    @JsonProperty("Votos a favor")
    private int aprovar;

    @JsonProperty("Votos contra")
    private int rejeitar;

    @JsonProperty("Tempo para votação (em minutos)")
    private int tempo;

    @JsonProperty("Proposta Ativa para votação")
    private boolean ativo;
}