package com.inovvet.core.entity.produto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "setor")
@Getter
@Setter
public class Setor extends AbstractEntity {
	
	@Size(max = 50)
	@NotBlank(message = "O Campo Descrição deve ser informado.")
	private String nome;
	
	private boolean ativo;
	
	public Setor() {
		this.ativo = true;
	}
}
