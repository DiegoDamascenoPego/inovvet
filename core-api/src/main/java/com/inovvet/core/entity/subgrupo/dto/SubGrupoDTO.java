package com.inovvet.core.entity.subgrupo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubGrupoDTO {
	
	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
	@NotNull
	private Boolean ativo;
	

}
