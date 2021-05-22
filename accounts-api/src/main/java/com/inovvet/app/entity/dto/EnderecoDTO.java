package com.inovvet.app.entity.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.inovvet.app.validator.CepValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO extends AbstractDTO {

	@NotBlank
	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	@CepValidation(message = "CEP informado não confere com o padrão estabelecido")
	private String cep;

	@NotNull
	@Valid
	private CidadeDTO cidade;

}
