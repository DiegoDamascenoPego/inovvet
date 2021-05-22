package com.inovvet.core.entity.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.inovvet.app.entity.Cidade;
import com.inovvet.app.entity.SimpleEntity;
import com.inovvet.app.validator.CepValidation;
import com.inovvet.core.enumerator.EnumTipoEndereco;

import lombok.Getter;
import lombok.Setter;

@Table(name = "pessoa_endereco")
@Getter
@Setter
@Entity
public class Endereco extends SimpleEntity {

	@Enumerated(EnumType.ORDINAL)
	private EnumTipoEndereco tipo;

	@CepValidation
	private String cep;

	@NotBlank
	private String rua;

	@NotBlank
	private String numero;

	@NotBlank
	private String bairro;

	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cidade_codigo")
	private Cidade cidade;

	public Endereco() {
		super();
	}

}
