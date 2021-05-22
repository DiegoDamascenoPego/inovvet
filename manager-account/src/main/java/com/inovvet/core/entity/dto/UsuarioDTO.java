package com.inovvet.core.entity.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO  {

	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@CPF(message = "O Campo CPF deve ser informado.")
	private String cpf;

	@NotBlank(message = "O Campo e-mail deve ser informado.")
	private String email;	

}
