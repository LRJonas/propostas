package com.guardioes.propostas.web.controller;

import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.service.PropostasService;
import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.PropostaResponseDto;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import com.guardioes.propostas.web.model.PropostaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

    private final PropostasService propostaService;
    private final PropostaMapper propostaMapper; // Injetar PropostaMapper

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaCreateDto dto) {
        Proposta proposta = propostaService.criar(PropostaMapper.paraProposta(dto));
        // Usar a instância injetada de PropostaMapper para chamar paraDto
        return ResponseEntity.status(HttpStatus.CREATED).body(propostaMapper.paraDto(proposta));
    }

    @PatchMapping("/iniciar-votacao")
    public ResponseEntity<PropostaResponseDto> iniciarVotacao(@RequestBody VotacaoInitDto dto) {
        Proposta proposta = propostaService.iniciarVotacao(dto);
        return ResponseEntity.ok(propostaMapper.paraDto(proposta));
    }

    @PostMapping("/votar")
    public ResponseEntity<PropostaResponseDto> votar(@RequestBody VotacaoDto dto) {
        Proposta proposta = propostaService.votar(dto);
        return ResponseEntity.ok(propostaMapper.paraDto(proposta));
    }

}

