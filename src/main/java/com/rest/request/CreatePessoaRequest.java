package com.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePessoaRequest {

	@NotNull(message = "Nome é obrigatorio, não pode ser nulo")
	@JsonProperty("NOME")
	@Size(max = 200)
	private String nome;

	@NotNull(message = "Cpf é obrigatorio, não pode ser nulo")
	@JsonProperty("CPF")
	@Size(max = 11)
	private String cpf;

	@JsonProperty("DATA_NASCIMENTO")
	private LocalDateTime dataNascimento;
}
