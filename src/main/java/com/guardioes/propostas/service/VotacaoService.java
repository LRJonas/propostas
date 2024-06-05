package com.guardioes.propostas.service;

import com.guardioes.propostas.entity.Votacao;
import com.guardioes.propostas.repository.VotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final VotacaoRepository votacaoRepository;

    public Votacao adicionar(Votacao votacao) {
        return votacaoRepository.save(votacao);
    }

}
