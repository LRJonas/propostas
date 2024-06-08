package com.guardioes.propostas.service;

import com.guardioes.propostas.entity.Votacao;
import com.guardioes.propostas.repository.VotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VotacaoServiceTest {
    @Mock
    private VotacaoRepository votacaoRepository;

    @InjectMocks
    private VotacaoService votacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeCriarPropostaSucesso() {
        Votacao votacao = new Votacao();
        votacao.setFuncionarioCpf("12345678901");

        when(votacaoRepository.save(any(Votacao.class))).thenReturn(votacao);
        Votacao resultado = votacaoService.adicionar(votacao);
        assertNotNull(resultado);
        verify(votacaoRepository, times(1)).save(votacao);
    }
}