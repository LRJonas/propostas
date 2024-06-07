package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoVotacaoIniciada extends RuntimeException {
    public ExcecaoVotacaoIniciada(String message){
        super(message);
    }
}