package com.inovvet.core.entity.loja.dto;

import com.inovvet.core.entity.cliente.dto.EnderecoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LojaContatoDTO {
	
	private String nomeFantasia;
	
	private String documento;	
	
	private EnderecoDTO endereco;

}
