package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoConexao extends RuntimeException {
    public ExcecaoConexao(String message){
        super(message);
    }
}
 