package com.inovvet.core.entity.produto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seguimento")
@Getter
@Setter
public class Seguimento extends AbstractEntity {
	
	@Size(max = 50)
	@NotBlank(message = "O Campo Descrição deve ser informado.")
	private String nome;
	
	@Size(max = 100)
	private String descricao;


	private boolean ativo;
	
	public  Seguimento() {
		this.ativo = true;
	}
	
	

}
