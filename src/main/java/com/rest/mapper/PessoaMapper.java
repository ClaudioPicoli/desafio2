package com.rest.mapper;

import com.rest.model.Pessoa;
import com.rest.response.PessoaResponse;

public final class PessoaMapper {

    /**
     * transforma o model Pessoa em PessoaResponse
     *
     * @param pessoa
     * @return
     */
    public static PessoaResponse map(Pessoa pessoa) {
        return PessoaResponse.builder()
            .idPessoa(pessoa.getIdPessoa())
            .cpf(pessoa.getCpf())
            .nome(pessoa.getNome())
            .dataNascimento(pessoa.getDataNascimento())
            .build();
    }
}
