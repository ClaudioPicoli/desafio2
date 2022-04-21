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
@EqualsAndHashCode(of = "idConta")
@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CONTA")
	@NotNull
	private Long idConta;

	@NotNull(message = "id da pessoa é obrigatorio, não pode ser nulo")
	@Column(name = "ID_PESSOA")
	private Long idPessoa;

	@Column(name = "SALDO", precision = 10, scale = 2)
	private BigDecimal saldo;

	@Column(name = "LIMITE_SAQUE_DIARIO", precision = 10, scale = 2)
	private BigDecimal limiteSaqueDiario;

	@Column(name = "FLAG_ATIVO")
	@NotNull(message = "Flag de ativo é obrigatorio, não pode ser nulo")
	private boolean flagAtivo;

	@Column(name = "TIPO_CONTA")
	@NotNull(message = "Tipo da Conta é obrigatorio, não pode ser nulo")
	private Integer tipoConta;

	@Column(name = "DATA_CRIACAO")
	@NotNull(message = "Data Criação é obrigatorio, não pode ser nulo ou vazio")
	private LocalDateTime dataCriacao;
}
