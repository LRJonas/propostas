package com.guardioes.propostas.client.funcionarios;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "funcionarios", url = "http://localhost:8080")
interface FuncionariosClient {

    @GetMapping("/funcionarios/{cpf}")
    Funcionario getFuncionarioByCpf(@PathVariable("cpf") String cpf);
}