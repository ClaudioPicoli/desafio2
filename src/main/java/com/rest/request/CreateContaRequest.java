package com.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateContaRequest {

	@NotNull(message = "id da pessoa é obrigatorio, não pode ser nulo")
	@JsonProperty("ID_PESSOA")
	private Long idPessoa;

	@JsonProperty("LIMITE_SAQUE_DIARIO")
	private BigDecimal limiteSaqueDiario;

	@NotNull(message = "tipo da conta é obrigatorio, não pode ser nulo")
	@JsonProperty("TIPO_CONTA")
	private Integer tipoConta;
}
