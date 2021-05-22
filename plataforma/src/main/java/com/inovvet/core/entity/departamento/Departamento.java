package com.inovvet.core.entity.departamento;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.produto.Seguimento;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departamento")
@Getter
@Setter
public class Departamento  extends AbstractEntity {

	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
	@NotNull(message = "Seguimento não foi informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seguimento_id")
	private Seguimento seguimento;
	
	@NotNull
	private Boolean ativo;

}
