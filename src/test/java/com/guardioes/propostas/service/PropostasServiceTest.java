package com.guardioes.propostas.service;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.entity.Votacao;
import com.guardioes.propostas.exception.ExcecaoCpfDuplicado;
import com.guardioes.propostas.exception.ExcecaoFuncionarioInvalido;
import com.guardioes.propostas.exception.ExcecaoPropostaInativa;
import com.guardioes.propostas.exception.ExcecaoPropostaInexistente;
import com.guardioes.propostas.repository.PropostaRepository;
import com.guardioes.propostas.repository.VotacaoRepository;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PropostasServiceTest {

    @Mock
    private PropostaRepository propostaRepository;
    @Mock
    private VotacaoRepository votacaoRepository;
    @Mock
    private FuncionariosClient funcionariosClient;
    @InjectMocks
    private PropostasService propostasService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeCriarPropostaSucesso() {
        Proposta proposta = new Proposta();
        proposta.setFuncionarioCpf("12345678901");

        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(new Funcionario());
        when(propostaRepository.save(any(Proposta.class))).thenReturn(proposta);
        Proposta resultado = propostasService.criar(proposta);
        assertNotNull(resultado);
        verify(propostaRepository, times(1)).save(proposta);
    }

    @Test
    public void testeCriarPropostaFuncionarioInvalido() {
        Proposta proposta = new Proposta();
        proposta.setFuncionarioCpf("12345678901");
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenThrow(FeignException.NotFound.class);
        assertThrows(ExcecaoFuncionarioInvalido.class, () -> propostasService.criar(proposta));
    }

    @Test
    public void testeIniciarVotacaoSucesso() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Teste");
        proposta.setFuncionarioCpf("00000000000");
        proposta.setAtivo(false);
        VotacaoInitDto dto = new VotacaoInitDto("Teste", "00000000000", 1);

        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.of(proposta));
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(new Funcionario());
        when(propostaRepository.save(any(Proposta.class))).thenReturn(proposta);
        Proposta resultado = propostasService.iniciarVotacao(dto);
        assertTrue(resultado.isAtivo());
        verify(propostaRepository, times(1)).save(proposta);
    }

    @Test
    public void testeIniciarVotacaoPropostaInvalida() {
        VotacaoInitDto dto = new VotacaoInitDto("Proposta Inexistente", "12345678901", 1);
        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.empty());
        assertThrows(ExcecaoPropostaInexistente.class, () -> propostasService.iniciarVotacao(dto));
    }

    @Test
    public void testeIniciarVotacaoFuncionarioInvalido() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Teste");
        proposta.setFuncionarioCpf("55555555555");
        VotacaoInitDto dto = new VotacaoInitDto("Teste", "55555555555", 1);
        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.of(proposta));
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenThrow(FeignException.NotFound.class);
        assertThrows(ExcecaoFuncionarioInvalido.class, () -> propostasService.iniciarVotacao(dto));
    }

    @Test
    public void testeVotarSucesso() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Teste");
        proposta.setFuncionarioCpf("00000000000");
        proposta.setAtivo(true);
        VotacaoDto dto = new VotacaoDto("Teste", "00000000000", Votacao.Voto.APROVAR);

        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.of(proposta));
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(new Funcionario());
        when(votacaoRepository.existsByTituloAndFuncionarioCpf(anyString(), anyString())).thenReturn(false);
        when(propostaRepository.save(any(Proposta.class))).thenReturn(proposta);
        Proposta resultado = propostasService.votar(dto);
        assertEquals(1, resultado.getAprovar());
        verify(propostaRepository, times(1)).save(proposta);
    }

    @Test
    public void testeVotarPropostaInativa() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Teste");
        proposta.setFuncionarioCpf("00000000000");
        proposta.setAtivo(false);

        VotacaoDto dto = new VotacaoDto("Teste", "00000000000", Votacao.Voto.APROVAR);
        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.of(proposta));
        assertThrows(ExcecaoPropostaInativa.class, () -> propostasService.votar(dto));
    }

    @Test
    public void testeVotarFuncionarioInvalido() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Teste");
        proposta.setFuncionarioCpf("99999999999");
        proposta.setAtivo(true);

        VotacaoDto dto = new VotacaoDto("Teste", "99999999999", Votacao.Voto.APROVAR);
        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.of(proposta));
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenThrow(FeignException.NotFound.class);
        assertThrows(ExcecaoFuncionarioInvalido.class, () -> propostasService.votar(dto));
    }

    @Test
    public void testeVotarCpfDuplicado() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Teste");
        proposta.setFuncionarioCpf("00000000000");
        proposta.setAtivo(true);

        VotacaoDto dto = new VotacaoDto("Teste", "00000000000", Votacao.Voto.APROVAR);
        when(propostaRepository.findByTitulo(anyString())).thenReturn(Optional.of(proposta));
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenReturn(new Funcionario());
        when(votacaoRepository.existsByTituloAndFuncionarioCpf(anyString(), anyString())).thenReturn(true);
        assertThrows(ExcecaoCpfDuplicado.class, () -> propostasService.votar(dto));
    }



}
