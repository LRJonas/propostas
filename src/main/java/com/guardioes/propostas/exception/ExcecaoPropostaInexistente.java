package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoPropostaInexistente extends RuntimeException {
    public ExcecaoPropostaInexistente(String message){
        super(message);
    }
}
