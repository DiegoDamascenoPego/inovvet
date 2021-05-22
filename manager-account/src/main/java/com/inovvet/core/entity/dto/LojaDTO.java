package com.inovvet.core.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.dto.AbstractDTO;
import com.inovvet.app.entity.dto.EnderecoDTO;
import com.inovvet.app.validator.DocumentoValidation;
import com.inovvet.core.entity.enumerator.EnumTipoLoja;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LojaDTO extends AbstractDTO {

	@NotBlank(message = "A Razão social  da loja deve ser informado")
	private String razaoSocial;

	@NotBlank(message = "O Nome da loja deve ser informado")
	private String nomeFantasia;

	@DocumentoValidation(message = "Documento informado inválido")
	private String documento;

//	@NotNull(message = "O Tipo da Loja deve ser informado")
	private EnumTipoLoja tipoLoja;

	@NotNull
	private EnderecoDTO endereco;

	private Boolean ativo;

}
