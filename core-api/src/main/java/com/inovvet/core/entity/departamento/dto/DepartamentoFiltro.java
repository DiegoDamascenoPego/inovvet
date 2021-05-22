package com.inovvet.core.entity.departamento.dto;

import com.inovvet.core.entity.base.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoFiltro {

	private String nome;

	private boolean ativo;

	private EntityDTO seguimento;
}
