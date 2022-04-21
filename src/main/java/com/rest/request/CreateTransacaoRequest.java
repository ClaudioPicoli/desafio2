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
public class CreateTransacaoRequest {

	@NotNull(message = "id da conta é obrigatorio, não pode ser nulo")
	@JsonProperty("ID_CONTA")
	private Long idConta;

	@NotNull(message = "Valor é obrigatorio, não pode ser nulo")
	@JsonProperty("VALOR")
	private BigDecimal valor;
}
