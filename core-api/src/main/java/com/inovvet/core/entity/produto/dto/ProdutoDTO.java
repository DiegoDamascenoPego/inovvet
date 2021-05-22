package com.inovvet.core.entity.produto.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.base.dto.SimpleEntityDTO;
import com.inovvet.core.entity.classificacao.dto.OpcaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

	@NotNull(message = "Tipo do Cadastro não foi informado")
	private SimpleEntityDTO tipo;

	@NotNull(message = "Unidade não foi informado")
	private SimpleEntityDTO unidade;
	
	private String ean;

	@Size(min = 3, max = 70)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@Size(min = 3, max = 120)
	@NotBlank(message = "O Campo Descrição  deve ser informado.")
	private String descricao;

	@NotNull(message = "Classificação não foi informado")
	private OpcaoDTO classificacao;
	
	private EntityDTO marca;
	
	private Set<PrecoDTO> precos;

	@NotNull
	private Boolean ativo;

}
