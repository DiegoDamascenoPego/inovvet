package com.inovvet.core.entity.pessoa.dto;

import javax.validation.constraints.NotNull;

import com.inovvet.app.validator.DocumentoValidation;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFiltroDTO {
	
	private EnumFinalidadePessoa tipo;
	
	@NotNull
	private String razaoSocialnome;
	
	@NotNull
	private String nomeFantasia;
	
	@DocumentoValidation(message = "O Campo Documento informado inválido")
	private String documento;
	
	@NotNull
	private Boolean ativo;

}
