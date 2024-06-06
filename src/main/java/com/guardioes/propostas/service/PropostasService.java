package com.guardioes.propostas.service;

import com.guardioes.propostas.client.funcionarios.Funcionario;
import com.guardioes.propostas.client.funcionarios.FuncionariosClient;
import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.entity.Votacao;
import com.guardioes.propostas.exception.ExcecaoCpfDuplicado;
import com.guardioes.propostas.exception.ExcecaoFuncionarioInvalido;
import com.guardioes.propostas.exception.ExcecaoPropostaInexistente;
import com.guardioes.propostas.repository.PropostaRepository;
import com.guardioes.propostas.repository.VotacaoRepository;
import com.guardioes.propostas.web.dto.VotacaoDto;
import com.guardioes.propostas.web.dto.VotacaoInitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class PropostasService {

    private final PropostaRepository propostaRepository;
    private final FuncionariosClient funcionariosClient;
    private final VotacaoRepository votacaoRepository;

    @Transactional
    public Proposta criar(Proposta proposta) {
        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(proposta.getFuncionarioCpf());
        if (funcionario == null) {
            throw new ExcecaoFuncionarioInvalido("Funcionário não encontrado");
        }
        return propostaRepository.save(proposta);
    }

    public Proposta iniciarVotacao(VotacaoInitDto dto) {
        Proposta proposta = propostaRepository.findByTitulo(dto.getPropostaTitulo())
                .orElseThrow(() -> new ExcecaoPropostaInexistente("Proposta não encontrada"));

        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(dto.getFuncionarioCpf());
        if (funcionario == null) {
            throw new ExcecaoFuncionarioInvalido("Funcionário não encontrado");
        }

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
                .orElseThrow(() -> new ExcecaoPropostaInexistente("Proposta não encontrada"));

        if (!proposta.isAtivo()) {
            throw new RuntimeException("A proposta não está ativa para votação");
        }

        Funcionario funcionario = funcionariosClient.getFuncionarioByCpf(dto.getCpf());
        if (funcionario == null) {
            throw new ExcecaoFuncionarioInvalido("Funcionário não encontrado");
        }

        boolean cpfJaVotou = votacaoRepository.existsByTituloAndFuncionarioCpf(dto.getTitulo(),dto.getCpf());
        if (cpfJaVotou) {
            throw new ExcecaoCpfDuplicado("Este CPF já votou nesta proposta");
        }

        Votacao voto = new Votacao();
        voto.setTitulo(dto.getTitulo());
        voto.setFuncionarioCpf(dto.getCpf());
        voto.setVoto(dto.getStatusVaga());
        votacaoRepository.save(voto);

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