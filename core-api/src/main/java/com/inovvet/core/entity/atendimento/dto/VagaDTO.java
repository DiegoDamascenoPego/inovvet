package com.inovvet.core.entity.atendimento.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagaDTO {

	private LocalDate data;

	private LocalTime hora;

	private Integer vaga;
	
	private Integer disponivel;
	
	public VagaDTO() {
		super();
	}

	public VagaDTO(LocalDate data, LocalTime hora, Integer vaga, Integer disponivel) {
		super();
		this.data = data;
		this.hora = hora;
		this.vaga = vaga;
		this.disponivel = disponivel;
	}

	public VagaDTO(LocalDate data, LocalTime hora) {
		super();
		this.data = data;
		this.hora = hora;
		this.vaga = 0;
		this.disponivel = 0;
		
	}
	
	

}
