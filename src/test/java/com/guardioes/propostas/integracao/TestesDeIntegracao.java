package com.guardioes.propostas.integracao;
import com.guardioes.propostas.repository.PropostaRepository;
import com.guardioes.propostas.repository.VotacaoRepository;
import com.guardioes.propostas.web.dto.PropostaCreateDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestesDeIntegracao{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        votacaoRepository.deleteAll();
        propostaRepository.deleteAll();
    }

    @Test
    public void testCriarProposta_Sucesso() throws Exception {
        PropostaCreateDto propostaDto = new PropostaCreateDto();
        propostaDto.setTitulo("Título da Proposta");
        propostaDto.setDescricao("Descrição da Proposta");
        propostaDto.setFuncionarioCpf("12345678900");

        ObjectMapper objectMapper = new ObjectMapper();
        String propostaJson = objectMapper.writeValueAsString(propostaDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/propostas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propostaJson));
        result.andExpect(status().isCreated());

    }



}