package com.inovvet.core.entity.categoria;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Getter
@Setter
public class Categoria extends AbstractEntity {

	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
//	@NotNull(message = "Departamento não foi informado")
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "departamento_id")
//	private Departamento departamento;
//	
	@NotNull
	private Boolean ativo;
	

}
