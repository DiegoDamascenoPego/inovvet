package com.inovvet.core.entity.loja;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.app.validator.DocumentoValidation;
import com.inovvet.core.enumerator.EnumTipoDocumento;
import com.inovvet.core.enumerator.EnumTipoLoja;

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

	private String ie;

	private String email;

	private String telefone;

	@Embedded
	private LojaEndereco endereco;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoLoja tipoLoja;

	@NotBlank
	private String token;

	@NotNull
	private Boolean ativo;

	@OneToOne(mappedBy = "loja", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ConfiguracaoLojaFiscal fiscal;

	@OneToOne(mappedBy = "loja", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private ConfiguracaoLojaPreco preco;

	public EnumTipoDocumento getTipoDocumento() {
		if (FunctionUtil.isEmpty(this.getDocumento())) {
			return null;
		}
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
