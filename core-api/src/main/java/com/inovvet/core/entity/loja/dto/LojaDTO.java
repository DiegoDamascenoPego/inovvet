package com.inovvet.core.entity.loja.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inovvet.app.validator.DocumentoValidation;
import com.inovvet.core.entity.cliente.dto.EnderecoDTO;
import com.inovvet.core.enumerator.EnumTipoDocumento;
import com.inovvet.core.enumerator.EnumTipoLoja;
import com.inovvet.core.enumerator.EnumTipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class LojaDTO  {

	@NotBlank(message = "A Razão social  da loja deve ser informado")
	private String razaoSocial;
	
	@NotBlank(message = "O Nome da loja deve ser informado")
	private String nomeFantasia;
	
	@DocumentoValidation(message = "Documento informado inválido")
	private String documento;	
	
	@NotNull
	private EnumTipoDocumento tipoDocumento;
	
	private String ie;
	
	private String email;
	
	private String telefone;
	
	@NotNull
	private EnumTipoLoja tipoLoja;
	
	@NotNull
	private EnderecoDTO endereco;
	
		
	private Boolean ativo;
	
	
	
	
}
