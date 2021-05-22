package com.inovvet.core.entity.pessoa.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.inovvet.app.validator.DocumentoValidation;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;
import com.inovvet.core.enumerator.EnumTipoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaPesquisaDTO {
	
private EnumFinalidadePessoa tipo;
	
	@Size(max = 150)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String razaoSocial;

	@Size(max = 150)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nomeFantasia;

	@Enumerated(EnumType.ORDINAL)
	private EnumTipoDocumento tipoDocumento;

	@DocumentoValidation(message = "O Campo Documento informado inválido")
	private String documento;
	
	@Size(max = 150)
	private String email;

}
