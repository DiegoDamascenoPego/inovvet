package com.inovvet.core.entity.grupo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Grupo")
@Getter
@Setter
public class Grupo  extends AbstractEntity {

	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
//	@NotNull(message = "Categoria não foi informado")
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "categoria_id")
//	private Categoria categoria;

	
	@NotNull
	private Boolean ativo;
	
}
