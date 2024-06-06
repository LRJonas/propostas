package com.guardioes.propostas.web.model;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.PropostaResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaMapper {

    private final FuncionariosClient funcionariosClient;

    @Autowired
    public PropostaMapper(FuncionariosClient funcionariosClient) {
        this.funcionariosClient = funcionariosClient;
    }

    public static Proposta paraProposta(PropostaCreateDto dto) {
        Proposta proposta = new ModelMapper().map(dto, Proposta.class);
        return proposta;
    }

    public static PropostaResponseDto paraDto(Proposta proposta) {
        PropostaResponseDto dto = new ModelMapper().map(proposta, PropostaResponseDto.class);

//        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
//        if (funcionario != null) {
//            dto.setNome(funcionario.getNome());
//        }

        return dto;
    }
}
