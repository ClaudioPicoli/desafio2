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
public class TransacaoResponse {

	@JsonProperty("id_conta")
	private Long idConta;

	@JsonProperty("valor")
	private BigDecimal valor;

	@JsonProperty("data_transacao")
	private LocalDateTime dataTransacao;

	public boolean isSaque () {
		return this.getValor() != null && BigDecimal.ZERO.compareTo(this.getValor()) > 0;
	}
}
