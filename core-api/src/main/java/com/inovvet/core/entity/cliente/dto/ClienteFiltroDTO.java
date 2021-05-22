package com.inovvet.core.entity.cliente.dto;

import javax.validation.constraints.NotNull;

import com.inovvet.core.entity.base.dto.AbstractDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFiltroDTO extends AbstractDTO {
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private Boolean ativo;
}
