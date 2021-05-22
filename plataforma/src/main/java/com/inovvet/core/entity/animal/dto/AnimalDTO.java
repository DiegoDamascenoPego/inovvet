package com.inovvet.core.entity.animal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inovvet.core.entity.animal.EnumTipoAnimal;
import com.inovvet.core.entity.animal.EnumTipoPelo;
import com.inovvet.core.enumerator.EnumSexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDTO  {	
	
	private Integer clienteId;
	
	@NotNull(message = "tipo não foi informado")
	private EnumTipoAnimal tipo;
	
	@NotBlank
	@Size(max = 150)
	private String nome;
	
	@NotNull(message = "Sexo não foi informado")
	private EnumSexo sexo;
	
	private RacaDTO raca;
	
	@NotNull(message = "cor não foi informado")
	private List<String> cores;
	
	private boolean castrado;
	
	@NotNull(message = "tipo do pêlo não foi informado")	
	private EnumTipoPelo pelo;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private boolean ativo;
	
	public AnimalDTO() {
		this.cores = new ArrayList<String>();
	}
	

}
