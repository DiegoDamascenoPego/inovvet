package com.inovvet.core.entity.dto;


import com.inovvet.core.entity.Cidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	
	private String rua;

	private String tipo;

	private String numero;

	private String bairro;

	private String complemento;

	private String cep;

	private Cidade cidade;

}
