package com.inovvet.core.service.pessoa;

import com.inovvet.core.entity.dto.EnderecoDTO;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;
import com.inovvet.core.enumerator.EnumTipoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaConsultaDTO {

	private EnumFinalidadePessoa tipo;

	private String razaoSocial;

	private String nomeFantasia;

	private EnumTipoDocumento tipoDocumento;

	private String documento;

	private String email;

	private EnderecoDTO endereco;

}
