package com.guardioes.propostas.web.controller;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.service.PropostasService;
import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.PropostaResponseDto;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import com.guardioes.propostas.web.exception.MensagemErro;
import com.guardioes.propostas.web.model.PropostaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Propostas", description = "Contém todas as operações relativas aos recursos para cadastro, iniciar votação, votar e consultar uma proposta")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

    private final PropostasService propostaService;
    private final FuncionariosClient funcionariosClient;

    @Operation(summary = "Cadastrar uma nova proposta",
            description = "Endpoint que cadastra uma nova proposta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proposta cadastrada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropostaResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "409", description = "Esta proposta já foi criada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "503", description = "Serviço indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
    })
    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody @Valid PropostaCreateDto dto) {
        Proposta proposta = propostaService.criar(PropostaMapper.paraProposta(dto));
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaMapper.paraDto(proposta,funcionario));
    }

    @Operation(summary = "Iniciar votação de proposta",
            description = "Endpoint para iniciar a votação de uma proposta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Votação iniciada!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropostaResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "409", description = "Esta proposta já está com a votação em andamento",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "503", description = "Serviço indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
    })
    @PatchMapping("/iniciar-votacao")
    public ResponseEntity<PropostaResponseDto> iniciarVotacao(@RequestBody @Valid VotacaoInitDto dto) {
        propostaService.iniciarVotacao(dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Registrar voto",
            description = "Endpoint para registrar voto dos funcionários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voto registrado!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropostaResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "409", description = "Este funcionários já registrou o voto",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "422", description = "Esta proposta não foi iniciada para votação",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "503", description = "Serviço indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
    })
    @PostMapping("/votar")
    public ResponseEntity<PropostaResponseDto> votar(@RequestBody @Valid VotacaoDto dto) {
        Proposta proposta = propostaService.votar(dto);
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaMapper.paraDto(proposta,funcionario));
    }

    @Operation(summary = "Buscar proposta",
            description = "Endpoint para buscar proposta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropostaResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "503", description = "Serviço indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
    })
    @GetMapping("/{propostaTitulo}")
    public ResponseEntity<PropostaResponseDto> buscarProposta(@PathVariable String propostaTitulo) {
        Proposta proposta = propostaService.buscar(propostaTitulo);
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        return ResponseEntity.ok(PropostaMapper.paraDto(proposta,funcionario));
    }
}

