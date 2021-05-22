package com.inovvet.core.entity.atendimento.dto;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.cliente.dto.CidadeDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	
	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	private CidadeDTO cidade;

}
