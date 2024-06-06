package com.guardioes.propostas.client.funcionarios;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "funcionarios", url = "http://localhost:8080/api/v1/funcionarios")
public interface FuncionariosClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}")
    Funcionario getFuncionarioByCpf(@PathVariable("cpf") String cpf);
}