package com.inovvet.core.service.cadastro.pessoa;

import com.inovvet.core.entity.cliente.dto.EnderecoCepDTO;
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

	private EnderecoCepDTO endereco;

}
