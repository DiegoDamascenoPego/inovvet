package com.inovvet.core.entity.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityDTO implements Abstract {

	protected Integer id;

	private String nome;

	public EntityDTO() {
		// TODO Auto-generated constructor stub
	}

	public EntityDTO(Integer id) {
		super();
		this.id = id;
	}

	public EntityDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
