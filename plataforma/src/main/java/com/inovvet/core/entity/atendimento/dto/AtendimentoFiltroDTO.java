package com.inovvet.core.entity.atendimento.dto;

import java.time.LocalDate;
import java.util.List;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.enumerator.EnumStatusAtendimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoFiltroDTO {
	
	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	
	private EntityDTO cliente;
	
	private List<EnumStatusAtendimento> status;
	
	private EntityDTO tipoAtendimento;

}
