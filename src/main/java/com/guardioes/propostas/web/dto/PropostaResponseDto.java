package com.guardioes.propostas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropostaResponseDto {
    private String titulo;
    private String descricao;
    private String nome;
    private String funcionarioCpf;
    private int aprovar;
    private int rejeitar;
    private int tempo;
    private boolean ativo;

}
