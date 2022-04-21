package com.rest.controller;

import com.rest.request.CreatePessoaRequest;
import com.rest.response.PessoaResponse;
import com.rest.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pessoa")
@Api(tags = "Funções de pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Criação de pessoas")
	public void createPessoa(final @RequestBody CreatePessoaRequest request) throws NoSuchElementException {
		pessoaService.create(request);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtem todas as contas")
	public List<PessoaResponse> getConta() throws NoSuchElementException {
		return pessoaService.obtemTodos();
	}
}
