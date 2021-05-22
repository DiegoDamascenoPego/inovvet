package com.inovvet.core.entity.vitrine.dto;

import com.inovvet.core.entity.loja.dto.LojaContatoDTO;
import com.inovvet.core.entity.produto.dto.ProdutoConsultaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VitrineDTO {
	
	private LojaContatoDTO loja;
	
	private ProdutoConsultaDTO produto;

}
