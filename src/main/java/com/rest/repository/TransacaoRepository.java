package com.rest.repository;

import com.rest.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByIdConta(Long idConta);

    List<Transacao> findAllByIdContaAndDataTransacaoBetween(Long idConta, LocalDateTime inicio, LocalDateTime fim);

}
