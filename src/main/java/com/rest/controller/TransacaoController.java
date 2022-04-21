package com.rest.controller;

import com.rest.response.TransacaoResponse;
import com.rest.service.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transacao")
@Api(tags = "Funções de transação")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "realiza operação de consulta de saldo em determinada conta")
	public List<TransacaoResponse> obtemPorPeriodo(final @RequestParam(value = "idConta") Long idConta,
												   final @RequestParam(value = "inicio", required = false) LocalDateTime inicio,
												   final @RequestParam(value = "fim", required = false) LocalDateTime fim) throws NoSuchElementException {
		return transacaoService.obtemPorPeriodo(idConta,inicio,fim);
	}
}
