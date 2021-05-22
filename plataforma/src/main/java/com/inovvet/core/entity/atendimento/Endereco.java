package com.inovvet.core.entity.atendimento;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inovvet.app.entity.Cidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {
	
	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cidade_codigo")
	private Cidade cidade;

}
