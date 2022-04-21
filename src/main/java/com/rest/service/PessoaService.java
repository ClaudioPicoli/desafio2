package com.rest.service;

import com.rest.mapper.PessoaMapper;
import com.rest.model.Pessoa;
import com.rest.repository.PessoaRepository;
import com.rest.request.CreatePessoaRequest;
import com.rest.response.PessoaResponse;
import lombok.var;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa create(CreatePessoaRequest request) {

		var pessoa = Pessoa.builder()
			.nome(request.getNome())
			.cpf(request.getCpf())
			.dataNascimento(request.getDataNascimento())
			.build();

		return pessoaRepository.save(pessoa);
	}

	//Metodo para obter todas as contas, mantido apenas para facilitar meus testes
	public List<PessoaResponse> obtemTodos () {
		var pessoas =  pessoaRepository.findAll();

		if (CollectionUtils.isEmpty(pessoas)) {
			return Collections.emptyList();
		}
		return pessoas.stream()
			.map(PessoaMapper::map)
			.collect(Collectors.toList());
	}
}
