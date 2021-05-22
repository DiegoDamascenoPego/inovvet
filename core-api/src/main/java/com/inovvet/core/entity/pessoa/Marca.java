package com.inovvet.core.entity.pessoa;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Marca extends AbstractEntity {

	@Size(max = 30)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
	@NotNull
	private Boolean ativo;

	public Marca() {
		super();
		this.ativo = true;
	}		

}
