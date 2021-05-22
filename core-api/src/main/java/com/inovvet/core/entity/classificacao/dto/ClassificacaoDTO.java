package com.inovvet.core.entity.classificacao.dto;

import javax.validation.constraints.NotNull;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassificacaoDTO {
	
	@NotNull(message = "Departamento não foi informado")
	private EntityDTO departamento;
	
	@NotNull(message = "Categoria não foi informado")
	private EntityDTO categoria;
	
	@NotNull(message = "Grupo não foi informado")
	private EntityDTO grupo;
	
	@NotNull(message = "Subgrupo não foi informado")
	private EntityDTO subgrupo;
	
	@NotNull
	private Boolean ativo;
}
