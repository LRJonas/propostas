package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoPropostaInativa extends RuntimeException {
    public ExcecaoPropostaInativa(String message){
        super(message);
    }
}
