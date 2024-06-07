package com.guardioes.propostas.web.exception;

import com.guardioes.propostas.exception.ExcecaoConexao;
import com.guardioes.propostas.exception.ExcecaoErroDesconhecido;
import com.guardioes.propostas.exception.ExcecaoFuncionarioInvalido;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExcecoesDecodificador implements ErrorDecoder {
    @Override
    public Exception decode(String method, Response response) {
        log.info("Decodificador de exceção: {}, {}", method, response);

        if(response.status() == 500 || response.status() == 503) {
            return new ExcecaoConexao("API Funcionários não disponível");
        }

        if(method.contains("getFuncionarioByCpf")) {
            return new ExcecaoFuncionarioInvalido("Funcionário não encontrado");
        }

        return new ExcecaoErroDesconhecido();
    }
}
