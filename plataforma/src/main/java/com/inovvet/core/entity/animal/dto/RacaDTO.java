package com.inovvet.core.entity.animal.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inovvet.core.entity.animal.EnumTipoAnimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RacaDTO {

	private Integer id;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoAnimal tipo;	

	private String descricao;

}
	