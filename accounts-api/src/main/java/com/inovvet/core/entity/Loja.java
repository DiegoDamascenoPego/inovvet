package com.inovvet.core.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.app.validator.DocumentoValidation;
import com.inovvet.core.entity.enumerator.EnumTipoDocumento;
import com.inovvet.core.entity.enumerator.EnumTipoLoja;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Loja extends AbstractEntity {

	private String razaoSocial;

	private String nomeFantasia;

	@Enumerated(EnumType.ORDINAL)
	private EnumTipoDocumento tipoDocumento;

	@DocumentoValidation(message = "O Campo Documento informado inválido")
	private String documento;

	@Enumerated(EnumType.ORDINAL)
	private EnumTipoLoja tipoLoja;

	@Embedded
	private LojaEndereco endereco;
	
	@NotBlank
	private String token;
	
	@NotNull
	private Boolean ativo;

	public EnumTipoDocumento getTipoDocumento() {
		return FunctionUtil.isCnpj(this.getDocumento()) ? EnumTipoDocumento.CNPJ : EnumTipoDocumento.CPF;

	}

	public void setDocumento(String value) {
		this.documento = FunctionUtil.removerMascaraTexto(value);

		this.tipoDocumento = FunctionUtil.isCnpj(this.documento) ? EnumTipoDocumento.CNPJ : EnumTipoDocumento.CPF;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

}
