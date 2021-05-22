package com.inovvet.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.inovvet.app.entity.SimpleEntity;
import com.inovvet.app.util.FunctionUtil;

import lombok.Getter;
import lombok.Setter;

@Table(name = "usuario_conta")
@Entity
@Getter
@Setter
public class UsuarioConta extends SimpleEntity {

	@JoinColumn(name = "conta_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Conta conta;

	@JoinColumn(name = "usuario_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	private String token;

	private Boolean ativo;
	
	public UsuarioConta() {
		
	}

	public UsuarioConta(Conta conta, Usuario usuario) {
		super();
		this.conta   = conta;
		this.usuario = usuario;
		this.token   = FunctionUtil.getToken();
		this.ativo   = true;
	}

}
