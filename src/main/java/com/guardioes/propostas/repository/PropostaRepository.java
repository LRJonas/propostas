package com.guardioes.propostas.repository;

import com.guardioes.propostas.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
