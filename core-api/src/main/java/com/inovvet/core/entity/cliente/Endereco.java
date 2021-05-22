package com.inovvet.core.entity.cliente;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.inovvet.app.entity.Cidade;
import com.inovvet.app.entity.SimpleEntity;
import com.inovvet.core.enumerator.EnumTipoEndereco;

import lombok.Getter;
import lombok.Setter;

@Table(name = "cliente_endereco")
@Getter
@Setter
@Entity
public class Endereco extends SimpleEntity {
	
	@OneToOne
    @JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoEndereco tipo;
	
	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cidade_codigo")
	private Cidade cidade;
	
}
