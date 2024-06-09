package com.guardioes.propostas.springdoc;

import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.PropostaResponseDto;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import com.guardioes.propostas.web.exception.MensagemErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Propostas", description = "Contém todas as operações relativas aos recursos para cadastro, iniciar votação, votar e consultar uma proposta")
public interface SpringDoc {
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
    ResponseEntity<PropostaResponseDto> criar(@RequestBody @Valid PropostaCreateDto dto);

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
    ResponseEntity<PropostaResponseDto> iniciarVotacao(@RequestBody @Valid VotacaoInitDto dto);

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
            @ApiResponse(responseCode = "409", description = "Este funcionário já registrou o voto",
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
    ResponseEntity<PropostaResponseDto> votar(@RequestBody @Valid VotacaoDto dto);

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
    ResponseEntity<PropostaResponseDto> buscarProposta(@PathVariable String propostaTitulo);
}
