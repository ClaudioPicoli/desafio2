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
public class ContaResponse {

	@JsonProperty("ID_CONTA")
	private Long idConta;

	@JsonProperty("ID_PESSOA")
	private Long idPessoa;

	@JsonProperty("SALDO")
	private BigDecimal saldo;

	@JsonProperty("LIMITE_SAQUE_DIARIO")
	private BigDecimal limiteSaqueDiario;

	@JsonProperty("FLAG_ATIVO")
	private boolean flagAtivo;

	@JsonProperty("TIPO_CONTA")
	private Integer tipoConta;

	@JsonProperty("DATA_CRIACAO")
	private LocalDateTime dataCriacao;

}
