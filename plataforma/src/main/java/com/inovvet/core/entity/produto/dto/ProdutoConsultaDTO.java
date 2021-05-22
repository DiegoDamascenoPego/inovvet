package com.inovvet.core.entity.produto.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoConsultaDTO {
	
	private Integer id;
	
	private String ean;

	private String nome;

	private String descricao;

	private String marca;
	
	private BigDecimal preco;
	
	private String url;

}
