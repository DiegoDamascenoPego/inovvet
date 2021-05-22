package com.inovvet.core.entity.cliente.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.app.validator.CepValidation;
import com.inovvet.core.entity.base.dto.Abstract;
import com.inovvet.core.enumerator.EnumTipoEndereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO  implements Abstract {
	
	private Integer id;

	@NotBlank
	private String rua;
	
	@NotNull(message="O tipo do endereço deve ser informado")
	private EnumTipoEndereco tipo;

	@NotBlank(message ="O campo número deve ser informado")
	private String numero;

	@NotBlank(message ="O campo bairro deve ser informado")
	private String bairro;

	private String complemento;

	@CepValidation(message = "CEP informado não confere com o padrão estabelecido")
	private String cep;

	@NotNull
	@Valid
	private CidadeDTO cidade;

}
