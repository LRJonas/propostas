package com.guardioes.propostas.web.dto;

import jakarta.persistence.Column;
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
    private String funcionarioNome;
    private String funcionarioCpf;
    private Integer aprovar;
    private Integer rejeitar;
    private boolean ativo;
}
