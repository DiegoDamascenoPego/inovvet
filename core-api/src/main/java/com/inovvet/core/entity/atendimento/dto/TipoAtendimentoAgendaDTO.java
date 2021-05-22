package com.inovvet.core.entity.atendimento.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.core.enumerator.EnumDiaSemana;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoAtendimentoAgendaDTO{
	
	private Integer id;

	@NotNull
	private EnumDiaSemana dia;

	@NotNull
	private LocalTime hora;

	@Size(min = 1)
	private Integer vaga;

}
