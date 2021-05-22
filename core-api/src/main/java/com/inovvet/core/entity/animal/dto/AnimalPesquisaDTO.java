package com.inovvet.core.entity.animal.dto;

import com.inovvet.core.entity.animal.EnumTipoAnimal;
import com.inovvet.core.enumerator.EnumSexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalPesquisaDTO {

	private Integer id;
	
	private EnumTipoAnimal tipo;
	
	private String nome;
	
	private EnumSexo sexo;
	
}
