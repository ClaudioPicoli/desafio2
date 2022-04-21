package com.rest.service;

import com.rest.mapper.ContaMapper;
import com.rest.model.Conta;
import com.rest.repository.ContaRepository;
import com.rest.repository.PessoaRepository;
import com.rest.request.CreateContaRequest;
import com.rest.request.CreateTransacaoRequest;
import com.rest.response.ContaResponse;
import com.rest.response.TransacaoResponse;
import lombok.var;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private TransacaoService transacaoService;

	public Conta create(CreateContaRequest request) {
		var idPessoa = request.getIdPessoa();
		validaPessoaExiste(idPessoa);

		var conta = Conta.builder()
			.idPessoa(idPessoa)
			.saldo(BigDecimal.ZERO)
			.limiteSaqueDiario(request.getLimiteSaqueDiario() != null ? request.getLimiteSaqueDiario() :BigDecimal.ZERO)
			.tipoConta(request.getTipoConta())
			.flagAtivo(true)
			.dataCriacao(LocalDateTime.now())
			.build();

		return contaRepository.save(conta);
	}

	private void validaPessoaExiste (Long idPessoa) {
		var pessoaExiste = pessoaRepository.findById(idPessoa).isPresent();

		if (!pessoaExiste) {
			throw new NoSuchElementException("Pessoa nao encontrada para " + idPessoa);
		}
	}

	//Metodo para obter todas as contas, mantido apenas para facilitar meus testes
	public List<ContaResponse> obtemTodos () {
		var contas =  contaRepository.findAll();

		if (CollectionUtils.isEmpty(contas)) {
			return Collections.emptyList();
		}
		return contas.stream()
			.map(ContaMapper::map)
			.collect(Collectors.toList());
	}

	public BigDecimal obtemSaldo(Long idConta) {
		var conta = obtemConta(idConta);
		return conta.getSaldo();
	}

	public void executaSaque(CreateTransacaoRequest request) {
		var idConta = request.getIdConta();
		var valor = request.getValor();
		var conta = obtemConta(idConta);

		if (!conta.isFlagAtivo()) {
			throw new AssertionError("Só é possivel executar saques de contas ativas");
		}

		if (BigDecimal.ZERO.compareTo(valor) < 0) {
			valor = valor.negate();
		}

		if (conta.getSaldo().compareTo(valor.negate()) < 0) {
			throw new AssertionError("Valor precisa ser inferior ao saldo");
		}

		var limiteDiario = conta.getLimiteSaqueDiario().add(obtemLimiteUtilizadoDia(idConta));
		if (limiteDiario.compareTo(valor.negate()) < 0) {
			throw new AssertionError("Valor precisa ser supeior ao limite de saque diario");
		}

		conta.setSaldo(conta.getSaldo().add(valor));
		contaRepository.save(conta);

		transacaoService.create(criaTransacaoRequest(idConta,valor));
	}

	//Metodo para obter todas as contas, mantido apenas para facilitar meus testes
	public void executaDeposito(CreateTransacaoRequest request) {
		var idConta = request.getIdConta();
		var valor = request.getValor();
		var conta = obtemConta(idConta);

		if (!conta.isFlagAtivo()) {
			throw new AssertionError("Só é possivel executar saques de contas ativas");
		}

		if (BigDecimal.ZERO.compareTo(valor) > 0) {
			valor = valor.negate();
		}

		conta.setSaldo(conta.getSaldo().add(valor));
		contaRepository.save(conta);

		transacaoService.create(criaTransacaoRequest(idConta,valor));
	}

	public Conta geraBloqueio(Long idConta) {
		var conta = obtemConta(idConta);
		conta.setFlagAtivo(false);
		return contaRepository.save(conta);
	}

	private Conta obtemConta (Long idConta) {
		return contaRepository.findById(idConta).orElseThrow(() -> new NoSuchElementException("Conta nao encontrada para " + idConta));
	}

	private BigDecimal obtemLimiteUtilizadoDia (Long idConta) {
		var inicio = LocalDateTime.now().with(LocalTime.MIN);
		var fim = LocalDateTime.now().with(LocalTime.MAX);

		var transacoes = transacaoService.obtemPorPeriodo(idConta, inicio, fim);

		if (CollectionUtils.isNotEmpty(transacoes)) {
			return transacoes.stream()
				.filter(TransacaoResponse::isSaque)
				.map(TransacaoResponse::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return BigDecimal.ZERO;
	}

	private CreateTransacaoRequest criaTransacaoRequest (Long idConta, BigDecimal valor) {
		return CreateTransacaoRequest.builder()
			.idConta(idConta)
			.valor(valor)
			.build();
	}
}
