package com.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idTransacao")
@Entity
@Table(name = "TRANSACAO")
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TRANSACAO")
	@NotNull
	private Long idTransacao;

	@NotNull(message = "Id da Conta é obrigatorio, não pode ser nulo ou vazio")
	@Column(name = "ID_CONTA")
	private Long idConta;

	@Column(name = "VALOR", precision = 10, scale = 2)
	@NotNull(message = "Valor da transação é obrigatorio, não pode ser nulo")
	private BigDecimal valor;

	@Column(name = "DATA_TRANSACAO")
	@NotNull(message = "Data Transação é obrigatorio, não pode ser nulo ou vazio")
	private LocalDateTime dataTransacao;
}
