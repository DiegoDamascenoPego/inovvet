package com.inovvet.core.entity.servico.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.produto.dto.PrecoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoDTO {
	
	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	private String descricao;

	@NotNull(message = "O campo Permite Agendamento não foi informado")
	private boolean permiteAgendamento;

	@NotNull(message = "O campo tempo de Execução não foi informado")
	private Integer tempoExecucao;

	@NotNull(message = "Seguimento não foi informado")
	private EntityDTO seguimento;

	private String ativo;
	
	private Set<PrecoDTO> precos;

}
