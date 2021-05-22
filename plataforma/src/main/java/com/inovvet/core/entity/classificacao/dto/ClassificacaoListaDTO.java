package com.inovvet.core.entity.classificacao.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassificacaoListaDTO {

	private Integer id;

	private String descricao;

	private List<OpcaoDTO> opcoes;

	public ClassificacaoListaDTO(Integer id, String descricao, List<OpcaoDTO> opcoes) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.opcoes = opcoes;
	}

}
