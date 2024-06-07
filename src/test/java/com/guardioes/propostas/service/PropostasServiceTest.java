package com.guardioes.propostas.service;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.exception.ExcecaoFuncionarioInvalido;
import com.guardioes.propostas.repository.PropostaRepository;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PropostasServiceTest {

    @Mock
    private PropostaRepository propostaRepository;
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
    public void testCriarProposta_FuncionarioInvalido() {
        Proposta proposta = new Proposta();
        proposta.setFuncionarioCpf("12345678901");
        when(funcionariosClient.getFuncionarioByCpf(anyString())).thenThrow(FeignException.NotFound.class);
        assertThrows(ExcecaoFuncionarioInvalido.class, () -> propostasService.criar(proposta));
    }


}
