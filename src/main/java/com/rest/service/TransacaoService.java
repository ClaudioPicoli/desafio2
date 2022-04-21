package com.rest.service;

import com.rest.mapper.TransacaoMapper;
import com.rest.model.Transacao;
import com.rest.repository.TransacaoRepository;
import com.rest.request.CreateTransacaoRequest;
import com.rest.response.TransacaoResponse;
import lombok.var;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	public Transacao create(CreateTransacaoRequest request) {
		var transacao = Transacao.builder()
			.idConta(request.getIdConta())
			.valor(request.getValor())
			.dataTransacao(LocalDateTime.now())
			.build();

		return transacaoRepository.save(transacao);
	}

	public List<TransacaoResponse> obtemPorPeriodo (Long idConta, LocalDateTime inicio, LocalDateTime fim) {

		if (inicio != null || fim != null) {
			var transacoes =  transacaoRepository.findAllByIdContaAndDataTransacaoBetween(idConta, inicio,fim);

			if (CollectionUtils.isEmpty(transacoes)) {
				return Collections.emptyList();
			}
			return transacoes.stream()
				.map(TransacaoMapper::map)
				.collect(Collectors.toList());
		}
		return obtemPorIdConta(idConta);
	}

	private List<TransacaoResponse> obtemPorIdConta (Long idConta) {
		var transacoes =  transacaoRepository.findAllByIdConta(idConta);

		if (CollectionUtils.isEmpty(transacoes)) {
			return Collections.emptyList();
		}
		return transacoes.stream()
			.map(TransacaoMapper::map)
			.collect(Collectors.toList());
	}


}
