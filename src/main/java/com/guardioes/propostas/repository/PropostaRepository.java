package com.guardioes.propostas.repository;

import com.guardioes.propostas.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);
}
