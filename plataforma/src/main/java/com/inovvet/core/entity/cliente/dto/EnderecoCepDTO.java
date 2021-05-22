package com.inovvet.core.entity.cliente.dto;

import com.inovvet.core.enumerator.EnumTipoEndereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoCepDTO {

	private String rua;

	private EnumTipoEndereco tipo;

	private String numero;

	private String bairro;

	private String complemento;

	private String cep;

	private CidadeDTO cidade;

}
