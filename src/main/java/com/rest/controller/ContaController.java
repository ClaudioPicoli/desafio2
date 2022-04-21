package com.rest.controller;

import com.rest.request.CreateContaRequest;
import com.rest.request.CreateTransacaoRequest;
import com.rest.response.ContaResponse;
import com.rest.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/conta")
@Api(tags = "Funções de conta")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Criação de Conta")
	public void createConta(final @RequestBody CreateContaRequest request) throws NoSuchElementException {
		contaService.create(request);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtem todas as contas")
	public List<ContaResponse> obtemConta() throws NoSuchElementException {
		return contaService.obtemTodos();
	}

	@GetMapping("/saldo")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "realiza operação de consulta de saldo em determinada conta")
	public BigDecimal obtemSaldo(final @RequestParam("idConta") Long idConta) throws NoSuchElementException {
		return contaService.obtemSaldo(idConta);
	}

	@PostMapping("/saque")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "realiza operação de saque em uma conta")
	public void executaSaque(final @RequestBody CreateTransacaoRequest request) throws NoSuchElementException {
		contaService.executaSaque(request);
	}

	@PostMapping("/deposito")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "realiza operação de deposito em uma conta")
	public void executaDeposito(final @RequestBody CreateTransacaoRequest request) throws NoSuchElementException {
		contaService.executaDeposito(request);
	}

	@PostMapping("/bloqueio")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "realiza o bloqueio de uma conta")
	public void geraBloqueio(final @RequestParam("idConta") Long idConta) throws NoSuchElementException {
		contaService.geraBloqueio(idConta);
	}
}
