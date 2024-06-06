package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoFuncionarioInvalido extends RuntimeException {
    public ExcecaoFuncionarioInvalido(String message){
        super(message);
    }
}
 