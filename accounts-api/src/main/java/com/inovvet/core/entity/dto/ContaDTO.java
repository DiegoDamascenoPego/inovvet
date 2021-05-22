package com.inovvet.core.entity.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.inovvet.app.entity.dto.EnderecoDTO;

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
	
	@NotNull
	@Valid
	private EnderecoDTO endereco;
	
	
	
//	@Valid
//	@NotNull
//	private LojaDTO loja;
	
	
	

}
