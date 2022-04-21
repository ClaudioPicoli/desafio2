package com.rest.service;

import com.rest.AbstractRestTest;
import com.rest.repository.ContaRepository;
import com.rest.request.CreateTransacaoRequest;
import javassist.NotFoundException;
import lombok.var;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class TransacaoServiceTest extends AbstractRestTest {

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private ContaRepository contaRepository;

	@Test
	@Rollback
	public void saveTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		var transacao = transacaoService.create(request);

		assertThat(transacao).isNotNull();
		assertEquals(request.getValor(), transacao.getValor());
	}

	@Test
	@Rollback
	public void ObtemTodosTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		transacaoService.create(request);

		var transacaoLista = transacaoService.obtemPorPeriodo(conta.getIdConta(), null, null);

		assertThat(transacaoLista).isNotNull();
		assertEquals(transacaoLista.size(), 1);
	}

	@Test
	@Rollback
	public void ObtemTodosByPeriodTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		transacaoService.create(request);

		var inicio = LocalDateTime.now().with(LocalTime.MIN);
		var fim = LocalDateTime.now().with(LocalTime.MAX);

		var transacaoLista = transacaoService.obtemPorPeriodo(conta.getIdConta(), inicio, fim);

		assertThat(transacaoLista).isNotNull();
		assertEquals(transacaoLista.size(), 1);
	}

	@Test
	@Rollback
	public void ObtemTodosByPeriodForaDeRangeTest() throws NotFoundException {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		transacaoService.create(request);

		var inicio = LocalDateTime.now().minusDays(1).with(LocalTime.MIN);
		var fim = LocalDateTime.now().minusDays(1).with(LocalTime.MAX);

		var transacaoLista = transacaoService.obtemPorPeriodo(conta.getIdConta(), inicio, fim);

		assertThat(transacaoLista).isNotNull();
		assertEquals(transacaoLista.size(), 0);
	}
}
