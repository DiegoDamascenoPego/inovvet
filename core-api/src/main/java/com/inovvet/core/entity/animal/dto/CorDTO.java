package com.inovvet.core.entity.animal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorDTO {

	private String nome;

	public CorDTO() {
		this.nome = "";
	}

	public CorDTO(String nome) {
		super();
		this.nome = nome;
	}

}
