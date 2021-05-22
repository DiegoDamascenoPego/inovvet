package com.inovvet.core.entity.produto.dto;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFiltroDTO {
	
	private String descricao;
	
	private boolean ativo;
	
	private EntityDTO tipo;

}
