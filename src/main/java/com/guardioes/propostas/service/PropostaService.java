package com.guardioes.propostas.service;

import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.entity.Votacao;
import com.guardioes.propostas.repository.PropostaRepository;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository propostaRepository;

    @Transactional
    public Proposta criar(Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    public Proposta iniciarVotacao(VotacaoInitDto dto) {
        Proposta proposta = propostaRepository.findByTitulo(dto.getPropostaTitulo())
                .orElseThrow(() -> new RuntimeException("Proposta not found"));

        proposta.setAtivo(true);
        proposta.setTempo(dto.getTempo());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                proposta.setAtivo(false);
                propostaRepository.save(proposta);
            }
        }, dto.getTempo() * 60000L);

        return propostaRepository.save(proposta);
    }

    @Transactional
    public Proposta votar(VotacaoDto dto) {
        Proposta proposta = propostaRepository.findByTitulo(dto.getTitulo())
                .orElseThrow(() -> new RuntimeException("Proposta not found"));

        if (!proposta.isAtivo()) {
            throw new RuntimeException("A proposta não está ativapara votação");
        }

        if (dto.getStatusVaga() == Votacao.StatusVaga.APROVAR) {
            proposta.setAprovar(proposta.getAprovar() + 1);
        } else if (dto.getStatusVaga() == Votacao.StatusVaga.REJEITAR) {
            proposta.setRejeitar(proposta.getRejeitar() + 1);
        } else {
            throw new RuntimeException("Invalid vote type");
        }

        return propostaRepository.save(proposta);
    }
}