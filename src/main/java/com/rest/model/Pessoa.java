package com.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idPessoa")
@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PESSOA")
	@NotNull
	private Long idPessoa;

	@Column(name = "NOME", length = 200)
	@NotBlank(message = "Nome da pessoa é obrigatorio, não pode ser nulo ou vazio")
	private String nome;

	@Size(max = 11)
	@Column(name = "CPF", length = 11)
	@NotBlank(message = "Cpf da pessoa é obrigatorio, não pode ser nulo ou vazio")
	//Não adicionarei validação se o cpf é valido para facilitar testes
	private String cpf;

	@Column(name = "DATA_NASCIMENTO")
	private LocalDateTime dataNascimento;
}
