package com.guardioes.propostas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoInitDto {
    private String propostaTitulo;
    private String funcionarioCpf;
}
