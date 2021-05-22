package com.inovvet.core.entity.loja;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inovvet.app.entity.Cidade;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class LojaEndereco {
	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "cidade_codigo")
	private Cidade cidade;
}
