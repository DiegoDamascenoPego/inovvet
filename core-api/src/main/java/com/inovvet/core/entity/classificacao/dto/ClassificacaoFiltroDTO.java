package com.inovvet.core.entity.classificacao.dto;

import java.util.Set;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassificacaoFiltroDTO {
	
	private Set<EntityDTO> departamentos;
	
	private Set<EntityDTO> categorias;
	
	private Set<EntityDTO> grupos;
	
	private Set<EntityDTO> subgrupos;
	
	private Boolean ativo;

}
