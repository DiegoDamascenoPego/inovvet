package com.inovvet.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.SimpleEntity;

import lombok.Getter;
import lombok.Setter;

@Table(name = "usuario_conta")
@Entity
@Getter
@Setter
public class UsuarioConta extends SimpleEntity {

	@JoinColumn(name = "conta_id")
	@OneToOne(fetch = FetchType.EAGER)
	private Conta conta;

	@JoinColumn(name = "usuario_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@NotNull(message = "O campo Token deve ser informado")
	private String token;

	private Boolean ativo;
	
	public UsuarioConta() {
		super();
	}

	public UsuarioConta(Conta conta, Usuario usuario) {
		super();
		this.conta = conta;
		this.usuario = usuario;
		this.ativo = true;
	}	

}
