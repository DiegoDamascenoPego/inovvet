package com.inovvet.core.entity.atendimento.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.core.entity.produto.dto.ProdutoConsultaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoAtendimentoDTO {
	
	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
	private Integer tempo;
	
	private Set<ProdutoConsultaDTO> produtos;
	
	private List<TipoAtendimentoAgendaDTO> agenda;
	
	@NotNull
	private Boolean ativo;

}
