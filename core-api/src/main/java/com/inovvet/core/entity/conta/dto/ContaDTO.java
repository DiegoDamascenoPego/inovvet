package com.inovvet.core.entity.conta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {
	
	@NotBlank(message = "O campo Nome é Obrigatório")
	private String nome;
	
	@NotBlank(message = "o campo Email é Obrigatório")
	private String email;
	
	@CPF(message = "O campo CPF está inválido")
	private String cpf;
	
	@NotBlank(message = "O campo Telefone está inválido")
	private String telefone;
	
	@NotBlank(message = "O campo Token é Obrigatório")
	private String token;
	
	@NotBlank(message = "O campo baseDados é Obrigatório")
	private String baseDados;

	@NotNull(message = "O campo Ativo está inválido")
	private Boolean ativo;
	
	
	

}
