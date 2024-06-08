package com.guardioes.propostas.web.controller;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.service.PropostasService;
import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.PropostaResponseDto;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import com.guardioes.propostas.web.model.PropostaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

    private final PropostasService propostaService;
    private final FuncionariosClient funcionariosClient;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody @Valid PropostaCreateDto dto) {
        Proposta proposta = propostaService.criar(PropostaMapper.paraProposta(dto));
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaMapper.paraDto(proposta,funcionario));
    }

    @PatchMapping("/iniciar-votacao")
    public ResponseEntity<PropostaResponseDto> iniciarVotacao(@RequestBody VotacaoInitDto dto) {
        propostaService.iniciarVotacao(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/votar")
    public ResponseEntity<PropostaResponseDto> votar(@RequestBody @Valid VotacaoDto dto) {
        Proposta proposta = propostaService.votar(dto);
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaMapper.paraDto(proposta,funcionario));
    }

    @GetMapping("/{propostaTitulo}")
    public ResponseEntity<PropostaResponseDto> buscarProposta(@PathVariable String propostaTitulo) {
        Proposta proposta = propostaService.buscar(propostaTitulo);
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        return ResponseEntity.ok(PropostaMapper.paraDto(proposta,funcionario));
    }
}

