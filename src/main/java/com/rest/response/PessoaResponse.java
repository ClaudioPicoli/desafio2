package com.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {

	@JsonProperty("ID_PESSOA")
	private Long idPessoa;


	@JsonProperty("NOME")
	private String nome;

	@JsonProperty("CPF")
	private String cpf;

	@JsonProperty("DATA_NASCIMENTO")
	private LocalDateTime dataNascimento;
}
