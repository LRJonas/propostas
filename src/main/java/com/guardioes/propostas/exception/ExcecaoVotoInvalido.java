package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoVotoInvalido extends RuntimeException {
    public ExcecaoVotoInvalido(String message){
        super(message);
    }
}