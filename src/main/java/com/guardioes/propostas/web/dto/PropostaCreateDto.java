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
public class PropostaCreateDto {
    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank
    @CPF(message = "O CPF do funcionário é inválido.")
    @Size(min = 11, max = 11, message = "O CPF do funcionário deve ter exatamente 11 caracteres.")
    private String funcionarioCpf;
}
