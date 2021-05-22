package com.inovvet.core.entity.usuario;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.inovvet.app.entity.SimpleEntity;
import com.inovvet.core.entity.loja.Loja;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_loja")
public class UsuarioLoja extends SimpleEntity {

	@JoinColumn(name = "loja_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Loja loja;

	@JoinColumn(name = "usuario_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	private Boolean ativo;

}
