package com.inovvet.core.entity.produto;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.enumerator.EnumUnidade;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(true)
public class Unidade extends AbstractEntity {
	
	@Size(min = 3, max = 70)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@Size(min = 3, max = 120)
	@NotBlank(message = "O Campo Desicao  deve ser informado.")
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private EnumUnidade unidadePadrao;
	
	@NotNull
	private Boolean ativo;

}
