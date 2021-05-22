package com.inovvet.core.entity.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.enumerator.EnumPerfilUsuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntity {
	
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@CPF(message = "O Campo CPF deve ser informado.")
	private String cpf;

	@NotBlank(message = "O Campo e-mail deve ser informado.")
	private String email;
	
	@NotNull(message = "O campo Perfil deve ser informado")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "perfil_id")
	private EnumPerfilUsuario perfil;
	
	@NotNull(message = "O campo token deve ser informado")
	private String token;

	@NotNull
	private Boolean ativo;

	
	public Usuario() {
		this.ativo = true;
		this.perfil = EnumPerfilUsuario.ADMINISTRADOR;
	}
	

}
