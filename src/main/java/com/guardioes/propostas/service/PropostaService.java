package com.guardioes.propostas.service;

import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository propostaRepository;

    @Transactional
    public Proposta criar(Proposta proposta) {
        return propostaRepository.save(proposta);
    }
}