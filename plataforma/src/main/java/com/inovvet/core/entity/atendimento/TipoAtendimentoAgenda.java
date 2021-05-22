package com.inovvet.core.entity.atendimento;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.enumerator.EnumDiaSemana;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TipoAtendimentoAgenda extends AbstractEntity {
	
	@NotNull(message = "Dia da Semana não informado")
	@Enumerated(EnumType.STRING)
	private EnumDiaSemana dia;
	
	@NotNull(message = "Horário de atendimento não informado")
	private LocalTime hora;
	
	private Integer vaga;
	
	public TipoAtendimentoAgenda() {
		super();
	}
	
	
}
