package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.model.Conta;
import com.rest.model.Pessoa;
import com.rest.repository.ContaRepository;
import com.rest.repository.PessoaRepository;
import lombok.var;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public abstract class AbstractRestTest {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ContaRepository contaRepository;

	protected <T> String toJson(T t) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(t);
	}
	
	protected <T> T toObject(JSONObject json, Class<T> _class) throws Exception {
		return new ObjectMapper().readValue(json.toString(), _class); 
	}

	protected MockMvc getMockMvc() {
		return this.mvc;
	}

	protected String buildApiPath(String path) {
		return path;
	}


	protected Pessoa criaPessoaPadrao () {
		var pessoa = Pessoa.builder()
			.cpf("32132132100")
			.dataNascimento(LocalDateTime.now())
			.nome("Teste1")
			.build();

		return pessoaRepository.save(pessoa);
	}

	protected Conta criaContaPadrao () {
		var pessoa = criaPessoaPadrao ();

		var conta = Conta.builder()
			.idPessoa(pessoa.getIdPessoa())
			.saldo(BigDecimal.ONE)
			.limiteSaqueDiario(BigDecimal.TEN)
			.tipoConta(1)
			.flagAtivo(true)
			.dataCriacao(LocalDateTime.now())
			.build();

		return contaRepository.save(conta);
	}
}
