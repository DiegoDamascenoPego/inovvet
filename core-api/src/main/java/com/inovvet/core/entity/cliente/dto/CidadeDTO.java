package com.inovvet.core.entity.cliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class CidadeDTO {

	private String codigo;
	
	private String nome;

	private String uf;

}
