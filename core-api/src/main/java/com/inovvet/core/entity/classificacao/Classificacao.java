package com.inovvet.core.entity.classificacao;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.categoria.Categoria;
import com.inovvet.core.entity.departamento.Departamento;
import com.inovvet.core.entity.grupo.Grupo;
import com.inovvet.core.entity.subgrupo.SubGrupo;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Table(name = "produto_classificacao")
@Getter
@Setter
public class Classificacao extends AbstractEntity {

	@NotNull(message = "Departamento não foi informado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@NotNull(message = "Categoria não foi informado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@NotNull(message = "Grupo não foi informado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;

	@NotNull(message = "Subgrupo não foi informado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subgrupo_id")
	private SubGrupo subgrupo;

	@NotNull
	private Boolean ativo;

	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String descricaoCompleta;

	public String getDescricaoCompleta() {

		return this.departamento.getNome() + " / " 
		+ this.categoria.getNome() + " / " 
		+ this.getGrupo().getNome()
		+ " / " + this.subgrupo.getNome();
	}

}
