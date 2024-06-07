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
public class VotacaoDto {
    private String titulo;
    private String cpf;
    private Votacao.Voto voto;
}