package com.guardioes.propostas.repository;

import com.guardioes.propostas.entity.Proposta;
import com.guardioes.propostas.entity.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
    boolean existsByTituloAndFuncionarioCpf(String titulo, String cpf);
}