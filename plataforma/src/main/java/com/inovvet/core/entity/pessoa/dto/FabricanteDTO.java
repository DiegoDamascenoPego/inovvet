package com.inovvet.core.entity.pessoa.dto;

import java.util.Set;

import javax.validation.Valid;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FabricanteDTO extends PessoaDTO {
	
	@Valid
	private Set<EntityDTO> marcas;

}
