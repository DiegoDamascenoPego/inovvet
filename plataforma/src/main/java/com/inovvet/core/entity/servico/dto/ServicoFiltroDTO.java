package com.inovvet.core.entity.servico.dto;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoFiltroDTO {
	
	private String nome;
	
	private boolean ativo;
	
	private EntityDTO seguimento;
	
	

}
