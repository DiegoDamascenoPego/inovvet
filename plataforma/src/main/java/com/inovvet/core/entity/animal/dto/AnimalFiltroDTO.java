package com.inovvet.core.entity.animal.dto;

import javax.validation.constraints.NotNull;

import com.inovvet.core.entity.animal.EnumTipoAnimal;
import com.inovvet.core.entity.cliente.dto.ClientePesquisaFiltroDTO;
import com.inovvet.core.enumerator.EnumSexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalFiltroDTO {

	private String nome;
	
	@NotNull(message = "Cliente não informado")
	private ClientePesquisaFiltroDTO cliente;
	
	@NotNull(message = "tipo não foi informado")
	private EnumTipoAnimal tipo;

	private EnumSexo sexo;
	
	private RacaDTO raca;
}
