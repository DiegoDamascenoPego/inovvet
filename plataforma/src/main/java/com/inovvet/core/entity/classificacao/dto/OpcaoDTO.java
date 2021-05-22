package com.inovvet.core.entity.classificacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpcaoDTO {

	private Integer id;

	private String descricao;

	private String descricaoCompleta;

	public OpcaoDTO() {
		super();
	}

	public OpcaoDTO(Integer id, String descricao, String descricaoCompleta) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.descricaoCompleta = descricaoCompleta;
	}

}
