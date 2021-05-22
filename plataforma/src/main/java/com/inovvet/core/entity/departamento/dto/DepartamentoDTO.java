package com.inovvet.core.entity.departamento.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDTO {
	
	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@NotNull(message = "Seguimento não foi informado")
	private EntityDTO seguimento;

	@NotNull
	private Boolean ativo;


}
