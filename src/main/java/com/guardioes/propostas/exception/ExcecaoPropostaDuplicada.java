package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoPropostaDuplicada extends RuntimeException {
    public ExcecaoPropostaDuplicada(String message){
        super(message);
    }
}
