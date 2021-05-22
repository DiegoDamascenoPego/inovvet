package com.inovvet.core.entity.subgrupo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subgrupo")
@Getter
@Setter
public class SubGrupo extends AbstractEntity {

	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

//	@NotNull(message = "Grupo não foi informado")
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "grupo_id")
//	private Grupo grupo;

	@NotNull
	private Boolean ativo;

}
