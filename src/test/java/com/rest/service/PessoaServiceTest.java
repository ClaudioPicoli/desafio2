package com.rest.service;

import com.rest.AbstractRestTest;
import com.rest.request.CreatePessoaRequest;
import lombok.var;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PessoaServiceTest extends AbstractRestTest {

	@Autowired
	private PessoaService pessoaService;

	@Test
	@Rollback
	public void saveTest()  {

		var request = CreatePessoaRequest.builder()
			.cpf("12312312388")
			.dataNascimento(LocalDateTime.now())
			.nome("Teste2")
			.build();

		var pessoa = pessoaService.create(request);

		assertThat(pessoa).isNotNull();
		assertThat(pessoa.getIdPessoa()).isNotNull();;
	}

	@Test
	@Rollback
	public void ObtemTodosTest() {

		criaPessoaPadrao();

		var pessoas = pessoaService.obtemTodos();

		assertThat(pessoas).isNotNull();
		assertEquals(pessoas.size(), 1);
	}
}
