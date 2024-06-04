package com.guardioes.propostas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropostaCreateDto {
    private String titulo;
    private String descricao;
    private String funcionarioCpf;
}
