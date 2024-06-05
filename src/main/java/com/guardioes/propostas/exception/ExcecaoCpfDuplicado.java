package com.guardioes.propostas.exception;

import lombok.Getter;

@Getter
public class ExcecaoCpfDuplicado extends RuntimeException {
    public ExcecaoCpfDuplicado(String message){
        super(message);
    }
}
