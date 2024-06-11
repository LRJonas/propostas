package com.guardioes.propostas.client.funcionarios;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "funcionarios", url = "${funcionarios.api.url}")
public interface FuncionariosClient {

    @GetMapping("/cpf/{cpf}")
    Funcionario getFuncionarioByCpf(@PathVariable("cpf") String cpf);
}