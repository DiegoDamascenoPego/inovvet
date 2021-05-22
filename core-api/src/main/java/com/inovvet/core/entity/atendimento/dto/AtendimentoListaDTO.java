package com.inovvet.core.entity.atendimento.dto;

import java.time.LocalDateTime;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.enumerator.EnumStatusAtendimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoListaDTO {
	
	private Integer id;
	
	private EntityDTO tipoAtendimento;
	
	private EntityDTO cliente;
	
	private EntityDTO animal;
	
	private String observacao;
	
	private LocalDateTime data;
	
	private String telefones;
	
	private EnumStatusAtendimento status;

}
