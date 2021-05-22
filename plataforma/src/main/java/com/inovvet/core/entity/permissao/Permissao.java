package com.inovvet.core.entity.permissao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.enumerator.EnumPerfilUsuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "perfil_recurso")
@Getter
@Setter
public class Permissao extends AbstractEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "perfil_id")
	@NotNull(message = "O campo Perfil deve ser informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumPerfilUsuario perfil;

	@Column(name = "recurso_nome")
	private String authority;
	
	@NotNull
	private Boolean ativo;

}
