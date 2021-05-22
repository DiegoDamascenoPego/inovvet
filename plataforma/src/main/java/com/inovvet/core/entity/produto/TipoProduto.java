package com.inovvet.core.entity.produto;

import javax.persistence.Cacheable;
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
@Cacheable(true)
public class TipoProduto extends AbstractEntity {
	
	@NotNull
	@Size(max = 30)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
	@NotNull
	@Size(max = 50)
	@NotBlank(message = "O campo Descricao deve ser informado.")
	private String descricao;
	
	@NotNull
	private Boolean permiteVender;
	
	@NotNull
	private Boolean controlarEstoque;
	
	@NotNull
	private Boolean ativo;

}
