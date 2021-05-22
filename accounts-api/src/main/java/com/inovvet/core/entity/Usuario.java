package com.inovvet.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.inovvet.app.entity.SimpleEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends SimpleEntity {

	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@CPF(message = "O Campo CPF deve ser informado.")
	private String cpf;

	@NotBlank(message = "O Campo e-mail deve ser informado.")
	private String email;

	private String password;

	@NotNull
	private boolean accountNonExpired;

	@NotNull
	private boolean accountNonLocked;

	@NotNull
	private boolean credentialsNonExpired;

	@NotNull
	private boolean enabled;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public Usuario() {
		super();
		
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}

	
}
