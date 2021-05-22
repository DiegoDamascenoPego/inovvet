package com.inovvet.core.entity.atendimento.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.enumerator.EnumStatusAtendimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoDTO {
	@NotNull(message = "Tipo Atendimento deve ser informado.")
	private EntityDTO tipoAtendimento;
	
	@NotNull(message = "Cliente deve ser informado")
	private EntityDTO cliente;
	
	@NotNull
	private EntityDTO animal;
	
	private String observacao;
	
	@NotNull(message = "Data do Atendimento deve ser informado")
	private LocalDate data;
	
	@NotNull(message = "Vaga do Atendimento deve ser informado")
	private VagaDTO vaga;
	
	@NotNull
	private EnumStatusAtendimento status;
	
	@NotNull
	private EnderecoDTO endereco;
	
	@NotEmpty
	private String telefones;

}
