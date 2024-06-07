package com.guardioes.propostas.controller;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.entity.Votacao;
import com.guardioes.propostas.service.PropostasService;
import com.guardioes.propostas.web.controller.PropostaController;
import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.PropostaResponseDto;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class PropostaControllerTest {
    @Mock
    private PropostasService propostaService;
    @Mock
    private FuncionariosClient funcionariosClient;
    @InjectMocks
    private PropostaController propostaController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test

    public void testeCriarProposta() {
        PropostaCreateDto dto = new PropostaCreateDto("AA", "BB", "12345678900");
        Proposta proposta = new Proposta();
        proposta.setTitulo("AA");
        proposta.setDescricao("BB");
        proposta.setFuncionarioCpf("12345678900");

        Funcionario funcionario = new Funcionario();

        when(propostaService.criar(any(Proposta.class))).thenReturn(proposta);
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(funcionario);

        ResponseEntity<PropostaResponseDto> resposta = propostaController.criar(dto);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("AA", resposta.getBody().getTitulo());
    }

    @Test
    public void testeIniciarVotacao() {
        VotacaoInitDto dto = new VotacaoInitDto("AA", "12345678900", 1);
        Proposta proposta = new Proposta();
        proposta.setTitulo("AA");
        proposta.setFuncionarioCpf("12345678900");
        proposta.setAtivo(true);

        Funcionario funcionario = new Funcionario();

        when(propostaService.iniciarVotacao(any(VotacaoInitDto.class))).thenReturn(proposta);
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(funcionario);
        ResponseEntity<PropostaResponseDto> resposta = propostaController.iniciarVotacao(dto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody().isAtivo());
    }

    @Test
    public void testeVotar() {
        VotacaoDto dto = new VotacaoDto("AA", "12345678900", Votacao.Voto.APROVAR);
        Proposta proposta = new Proposta();
        proposta.setTitulo("AA");
        proposta.setFuncionarioCpf("12345678900");
        proposta.setAprovar(1);

        Funcionario funcionario = new Funcionario();

        when(propostaService.votar(any(VotacaoDto.class))).thenReturn(proposta);
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(funcionario);
        ResponseEntity<PropostaResponseDto> resposta = propostaController.votar(dto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(1, resposta.getBody().getAprovar());
    }

}
