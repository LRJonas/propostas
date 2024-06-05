package com.guardioes.propostas.web.dto;

import com.guardioes.propostas.entity.Votacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoCreateDto {
    private String propostaTitulo;
    private String funcionarioCpf;
    private Votacao.StatusVaga voto;
}
