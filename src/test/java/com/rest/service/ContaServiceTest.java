package com.rest.service;

import com.rest.AbstractRestTest;
import com.rest.request.CreateContaRequest;
import com.rest.request.CreateTransacaoRequest;
import lombok.var;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContaServiceTest extends AbstractRestTest {

	@Autowired
	private ContaService contaService;

	@Test
	@Rollback
	public void saveTest() {

		var pessoa = criaPessoaPadrao();

		var request = CreateContaRequest.builder()
			.idPessoa(pessoa.getIdPessoa())
			.limiteSaqueDiario(BigDecimal.TEN)
			.tipoConta(1)
			.build();

		var conta = contaService.create(request);

		assertThat(conta).isNotNull();
		assertEquals(request.getIdPessoa(), conta.getIdPessoa());
	}


	@Test(expected = InvalidDataAccessApiUsageException.class)
	@Rollback
	public void saveSemPessoaTest() {

		var request = CreateContaRequest.builder()
			.idPessoa(null)
			.limiteSaqueDiario(BigDecimal.TEN)
			.tipoConta(1)
			.build();

		contaService.create(request);
	}

	@Test
	@Rollback
	public void ObtemTodosTest()  {

		criaContaPadrao();

		var contas = contaService.obtemTodos();

		assertThat(contas).isNotNull();
		assertEquals(contas.size(), 1);
	}

	@Test
	@Rollback
	public void ObtemSaldoTest() {

		var conta = criaContaPadrao();

		var saldo = contaService.obtemSaldo(conta.getIdConta());

		assertThat(saldo).isNotNull();
		assertEquals(saldo, BigDecimal.ONE);
	}

	@Test
	@Rollback
	public void executaDepositoTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		contaService.executaDeposito(request);

		var saldo = contaService.obtemSaldo(conta.getIdConta());

		assertThat(saldo).isNotNull();
		assertEquals(BigDecimal.valueOf(2), saldo);
	}

	@Test
	@Rollback
	public void geraBloqueioTEst() {

		var conta = criaContaPadrao();
		var flafAtivo = contaService.geraBloqueio(conta.getIdConta()).isFlagAtivo();

		assertFalse(flafAtivo);
	}

	@Test(expected = AssertionError.class)
	@Rollback
	public void executaDepositoContaInativaTest() {

		var conta = criaContaPadrao();
		conta = contaService.geraBloqueio(conta.getIdConta());

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		contaService.executaDeposito(request);
	}

	@Test
	@Rollback
	public void executaSaqueTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		contaService.executaSaque(request);

		var saldo = contaService.obtemSaldo(conta.getIdConta());

		assertThat(saldo).isNotNull();
		assertEquals(BigDecimal.ZERO, saldo);
	}

	@Test(expected = AssertionError.class)
	@Rollback
	public void executaSaqueContaInativaTest() {

		var conta = criaContaPadrao();
		conta = contaService.geraBloqueio(conta.getIdConta());

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.ONE)
			.build();

		contaService.executaSaque(request);;
	}

	@Test(expected = AssertionError.class)
	@Rollback
	public void executaSaqueAcimaSaldoTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.valueOf(3))
			.build();

		contaService.executaSaque(request);;
	}

	@Test(expected = AssertionError.class)
	@Rollback
	public void executaSaqueAcimaLimiteTest() {

		var conta = criaContaPadrao();

		var request = CreateTransacaoRequest.builder()
			.idConta(conta.getIdConta())
			.valor(BigDecimal.valueOf(20))
			.build();

		contaService.executaSaque(request);;
	}
}
