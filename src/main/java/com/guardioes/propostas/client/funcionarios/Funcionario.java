package com.guardioes.propostas.client.funcionarios;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    private Long id;
    private String cpf;
    private String nome;
    private boolean ativo;
}
