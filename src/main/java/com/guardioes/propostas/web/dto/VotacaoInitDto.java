package com.guardioes.propostas.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoInitDto {
    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 5, max = 200, message = "O título deve ter no mínimo 5 caracteres e no máximo 200 caracteres")
    private String propostaTitulo;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "O CPF do funcionário é inválido.")
    @Size(min = 11, max = 11, message = "O CPF do funcionário deve ter exatamente 11 caracteres.")
    private String funcionarioCpf;


    private long tempo = 1 ;
}
