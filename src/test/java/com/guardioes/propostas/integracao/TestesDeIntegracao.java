package com.guardioes.propostas.integracao;
import com.guardioes.propostas.entity.Proposta;
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
public class TestesDeIntegracao {

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
    public void testeCriarPropostaSucesso() throws Exception {
        PropostaCreateDto propostaDto = new PropostaCreateDto();
        propostaDto.setTitulo("teste");
        propostaDto.setDescricao("teste");
        propostaDto.setFuncionarioCpf("12345678900");

        ObjectMapper objectMapper = new ObjectMapper();
        String propostaJson = objectMapper.writeValueAsString(propostaDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/propostas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propostaJson));
        result.andExpect(status().isCreated());

    }

    @Test
    public void testeIniciarVotacaoSucesso() throws Exception {
        PropostaCreateDto propostaDto = new PropostaCreateDto();
        propostaDto.setTitulo("teste");
        propostaDto.setDescricao("teste");
        propostaDto.setFuncionarioCpf("12345678900");

        mockMvc.perform(post("/api/v1/propostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(propostaDto)))
                .andExpect(status().isCreated());
        VotacaoInitDto votacaoInitDto = new VotacaoInitDto("teste", "12345678900", 1);
        String jsonRequest = objectMapper.writeValueAsString(votacaoInitDto);

        mockMvc.perform(patch("/api/v1/propostas/iniciar-votacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void testeVotarSucesso() throws Exception {
        Proposta proposta = new Proposta();
        proposta.setTitulo("teste");
        proposta.setDescricao("teste");
        proposta.setFuncionarioCpf("12345678900");
        proposta.setAtivo(true);
        propostaRepository.save(proposta);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/propostas/votar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"teste\",\"cpf\":\"12345678900\",\"voto\":\"APROVAR\"}"))
                .andExpect(status().isOk());

    }
}