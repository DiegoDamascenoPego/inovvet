package com.inovvet.core.entity.cliente.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.core.entity.base.dto.Abstract;
import com.inovvet.core.enumerator.EnumTipoTelefone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDTO implements Abstract {

	private Integer id;

	@NotNull(message = "O tipo do telefone não foi informado")
	private EnumTipoTelefone tipo;

	@NotBlank(message = "O campo número deve ser informado")
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
		if (!(obj instanceof TelefoneDTO))
			return false;
		TelefoneDTO other = (TelefoneDTO) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
