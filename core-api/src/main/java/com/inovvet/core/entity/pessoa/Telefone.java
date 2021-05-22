package com.inovvet.core.entity.pessoa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.SimpleEntity;
import com.inovvet.core.enumerator.EnumTipoTelefone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "pessoa_telefone")
@Entity
public class Telefone extends SimpleEntity {

	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private EnumTipoTelefone tipo;

	@NotBlank
	private String numero;

	private String observacao;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Telefone))
			return false;
		Telefone other = (Telefone) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
