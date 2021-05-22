package com.inovvet.core.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRegistrarDTO {

	private String nome;

	private String cpf;

	private String email;

	private String token;
	
	private Boolean ativo;

}
